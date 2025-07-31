package tpi.backend.logistica.services;

import java.sql.Timestamp;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import tpi.backend.logistica.clients.ContenedorClient;
import tpi.backend.logistica.clients.DepositoClient;
import tpi.backend.logistica.dtos.ContenedorDTO;
import tpi.backend.logistica.dtos.DatosRespuestaPosteo;
import tpi.backend.logistica.dtos.DepositoDTO;
import tpi.backend.logistica.dtos.DistanciaDTO;
import tpi.backend.logistica.dtos.PostSolicitudDTO;
import tpi.backend.logistica.dtos.RespuestaCotizacionDTO;
import tpi.backend.logistica.dtos.Tramo_rutaDTO;
import tpi.backend.logistica.entities.Ciudad;
import tpi.backend.logistica.entities.TarifaBase;
import tpi.backend.logistica.entities.TipoTramo;
import tpi.backend.logistica.entities.Tramo_Ruta;
@Service
@Slf4j
public class PlanificacionService {
    
    private final TarifaKMService tarifaKMService;
    private final TarifaBaseService tarifaBaseService;
    private final CiudadService ciudadService;
    private final TramoService tramoService;
    private final DepositoClient depositoClient;
    private final ContenedorClient contenedorClient;

    @Value("${maps.service.url}")
    private String mapsServiceUrl;

    public PlanificacionService(TarifaBaseService tarifaBaseService, TarifaKMService tarifaKMService, CiudadService ciudadService, TramoService tramoService, 
                DepositoClient depositoClient, ContenedorClient contenedorClient){
        this.tarifaKMService = tarifaKMService;
        this.tarifaBaseService = tarifaBaseService;
        this.ciudadService = ciudadService;
        this.tramoService = tramoService;
        this.depositoClient = depositoClient;
        this.contenedorClient = contenedorClient;
    }
    //corregir calculo de tarifas
    public ResponseEntity<RespuestaCotizacionDTO> cotizarSolicitud(double pesoContenedor, double volumenContenedor, Ciudad ciudadOrigen, Ciudad ciudadDestino, 
                        double latitudDeposito, double longitudDeposito){
        
        ResponseEntity<DistanciaDTO> distancia1 = obtenerDistancia(ciudadOrigen, latitudDeposito,longitudDeposito);
        ResponseEntity<DistanciaDTO> distancia2 = obtenerDistancia(ciudadDestino, latitudDeposito,longitudDeposito);
                            
        if (!distancia1.getStatusCode().is2xxSuccessful() || !distancia2.getStatusCode().is2xxSuccessful()) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Error al obtener distancias");
        }

        DistanciaDTO d1 = distancia1.getBody();
        DistanciaDTO d2 = distancia2.getBody();

        double tarifaKM = obtenerTarifa(pesoContenedor, volumenContenedor);

        TarifaBase tarifaBase = tarifaBaseService
            .obtenerTarifaBase(1)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));

        double monto = ((d1.getKilometros() + d2.getKilometros()) * tarifaKM ) + tarifaBase.getTarifa();

        String duracion = sumarDistancias(d1, d2);

        RespuestaCotizacionDTO respuesta = new RespuestaCotizacionDTO(monto, duracion);

        return ResponseEntity.ok(respuesta);

    }

    private Double obtenerTarifa(double pesoContenedor, double volumenContenedor) {
        Double prueba = tarifaKMService.obtenerTarifa(pesoContenedor, volumenContenedor);
        return prueba != null ? prueba : null;
    }

    public ResponseEntity<DistanciaDTO> obtenerDistancia(Ciudad ciudad, double latitudDeposito, double longitudDeposito){
        
        try{
            RestTemplate template = new RestTemplate();

            String origen = ciudad.getLatitud() + "," + ciudad.getLongitud();
            String destino = latitudDeposito + "," + longitudDeposito;


            String url = UriComponentsBuilder
                .fromHttpUrl(mapsServiceUrl)
                .queryParam("origen", origen)
                .queryParam("destino", destino)
                .toUriString();

            ResponseEntity<DistanciaDTO> res = template.getForEntity(url, DistanciaDTO.class);

            if (res.getStatusCode().is2xxSuccessful()){
                log.debug("Distancia Obtenida Correctamente: ", res.getBody());
                return res;
            
            } else {
                log.warn("Respuesta no exitosa: ", res.getStatusCode());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (HttpClientErrorException e){
            log.error("Error en la peticion", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public String sumarDistancias(DistanciaDTO d1, DistanciaDTO d2){
        int hora1 = obtenerHoras(d1);
        int hora2 = obtenerHoras(d2);

        int minutos1 = obtenerMinutos(d1);
        int minutos2 = obtenerMinutos(d2);

        int horas = hora1 + hora2;
        int minutos = minutos1 + minutos2;

        if(minutos > 59){
            horas++;
            minutos = minutos - 60;
        }

        String duracion = horas + " " + "horas" + " " + minutos + " " + "minutos";

        return duracion;
    }

    public Double sumarDistanciasHoras(DistanciaDTO d1, DistanciaDTO d2){
        Double hora1 = (double)obtenerHoras(d1);
        Double hora2 = (double)obtenerHoras(d2);

        int minutos1 = obtenerMinutos(d1);
        int minutos2 = obtenerMinutos(d2);

        Double horas = hora1 + hora2 + (minutos1 + minutos2)/60;
        return horas;
    }

    public int obtenerHoras(DistanciaDTO d1){
        String[] horas1 = d1.getDuracionHoras().split(" ");
        return Integer.parseInt(horas1[0].trim());
    }

    public int obtenerMinutos(DistanciaDTO d1){
        String[] minutos1 = d1.getDuracionMinutos().split(" ");
        return Integer.parseInt(minutos1[0].trim());
    }

    public Timestamp obtenerLlegada(Timestamp salida, DistanciaDTO d1){

        int horasSalida = obtenerHoras(d1);
        int minutosSalida = obtenerMinutos(d1);

        long tiempoAdicional1 = (horasSalida * 60 + minutosSalida) * 60 * 1000;

        return new Timestamp(salida.getTime() + tiempoAdicional1);
    }

    public ResponseEntity<DatosRespuestaPosteo> crearRutas(PostSolicitudDTO solicitudPost)     {

    DepositoDTO deposito = depositoClient
            .getDepositoById(solicitudPost.getidDeposito());  // ② getIdCiudadDeposito()

    ContenedorDTO contenedor = contenedorClient
            .getContenedorById(solicitudPost.getidContenedor());
            
        // 3. Usamos el id de ciudad que nos devolvió el depósito
    Ciudad ciudadDeposito = ciudadService
            .obtenerCiudad(deposito.getIdCiudad())                // ③ deposito.getIdCiudad()
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Ciudad del depósito no encontrada"));
    
    // 1. Cargo ciudad origen y destino
    Ciudad ciudadOrigen = ciudadService
        .obtenerCiudad(solicitudPost.getIdCiudadOrigen())
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Ciudad origen no encontrada"));

    Ciudad ciudadDestino = ciudadService
        .obtenerCiudad(solicitudPost.getIdCiudadDestino())
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Ciudad destino no encontrada"));

    // 3. Consulto distancias
    ResponseEntity<DistanciaDTO> distancia1 = 
        obtenerDistancia(ciudadOrigen,  deposito.getLatitud(), deposito.getLongitud());
    ResponseEntity<DistanciaDTO> distancia2 = 
        obtenerDistancia(ciudadDestino, deposito.getLatitud(), deposito.getLongitud());

    if (!distancia1.getStatusCode().is2xxSuccessful() ||
        !distancia2.getStatusCode().is2xxSuccessful()) {
        throw new ResponseStatusException(
            HttpStatus.BAD_GATEWAY, "Error al obtener distancias");
    }

    DistanciaDTO d1 = distancia1.getBody();
    DistanciaDTO d2 = distancia2.getBody();

    // 4. Calculo tiempos de salida y llegada
    Timestamp fechaSalida1  = new Timestamp(System.currentTimeMillis());
    Timestamp fechaLlegada1 = obtenerLlegada(fechaSalida1, d1);

    Timestamp fechaSalida2  = fechaLlegada1;
    Timestamp fechaLlegada2 = obtenerLlegada(fechaSalida2, d2);

    // 5. Construyo los tramos
    Tramo_Ruta tramo1 = new Tramo_Ruta(
        null,
        solicitudPost.getIdSolicitud(),
        1,
        TipoTramo.origen_deposito,
        ciudadOrigen,
        ciudadDeposito,
        fechaSalida1,
        fechaLlegada1,
        null,
        null
    );

    Tramo_Ruta tramo2 = new Tramo_Ruta(
        null,
        solicitudPost.getIdSolicitud(),
        2,
        TipoTramo.deposito_origen,
        ciudadDeposito,
        ciudadDestino,
        fechaSalida2,
        fechaLlegada2,
        null,
        null
    );

    // 6. Persisto los tramos
    Tramo_rutaDTO respuesta1 = tramoService.crearTramo(tramo1);
    Tramo_rutaDTO respuesta2 = tramoService.crearTramo(tramo2);

    double tiempoEstimadoHoras = sumarDistanciasHoras(d1, d2);

    Double tarifaKM = obtenerTarifa(contenedor.getPeso(), contenedor.getVolumen());

    if (tarifaKM == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró una tarifa adecuada, por favor cree una");
    }

    TarifaBase tarifaBase = tarifaBaseService
        .obtenerTarifaBase(1)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));

    double monto = ((d1.getKilometros() + d2.getKilometros()) * tarifaKM ) + tarifaBase.getTarifa();

    
    
    // 7. Armo la respuesta
    DatosRespuestaPosteo respuesta = new DatosRespuestaPosteo(respuesta1, respuesta2, tiempoEstimadoHoras, monto);

    System.out.println("Respuesta" + respuesta);
    return ResponseEntity.ok(respuesta);
}


}
