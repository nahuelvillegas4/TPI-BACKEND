package tpi.backend.logistica.controllers;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import tpi.backend.logistica.dtos.DatosRespuestaPosteo;
import tpi.backend.logistica.dtos.DistanciaDTO;
import tpi.backend.logistica.dtos.PostSolicitudDTO;
import tpi.backend.logistica.dtos.RespuestaCotizacionDTO;
import tpi.backend.logistica.entities.Ciudad;
import tpi.backend.logistica.entities.Contenedor;
import tpi.backend.logistica.entities.Deposito;
import tpi.backend.logistica.entities.Solicitud;
import tpi.backend.logistica.entities.TarifaBase;
import tpi.backend.logistica.entities.TarifaKM;
import tpi.backend.logistica.entities.TramoRuta;
import tpi.backend.logistica.services.CiudadService;
import tpi.backend.logistica.services.ContenedorService;
import tpi.backend.logistica.services.DepositoService;
import tpi.backend.logistica.services.SolicitudService;
import tpi.backend.logistica.services.TarifaBaseService;
import tpi.backend.logistica.services.TarifaKMService;

@RestController
@Slf4j
@RequestMapping("/planificacion")
public class SolicitudControler {

    @Value("${maps.service.url}")
    private String mapsServiceUrl;

    private final ContenedorService contenedorService;
    private final CiudadService ciudadService;
    private final DepositoService depositoService;
    private final TarifaBaseService tarifaBaseService;
    private final TarifaKMService tarifaKMService;
    private final SolicitudService solicitudService;

    public SolicitudControler(ContenedorService contenedorService, CiudadService ciudadService, DepositoService depositoService,
        TarifaBaseService tarifaBaseService, TarifaKMService tarifaKMService, SolicitudService solicitudService){
        this.contenedorService = contenedorService;
        this.ciudadService = ciudadService;
        this.depositoService = depositoService;
        this.tarifaBaseService = tarifaBaseService;
        this.tarifaKMService = tarifaKMService;
        this.solicitudService = solicitudService;
    }

    //ResponseEntity<RespuestaCotizacionDTO>
    @GetMapping 
    public ResponseEntity<RespuestaCotizacionDTO> cotizarSolicitud(
        @RequestParam long idContenedor,
        @RequestParam long idCiudadOrigen,
        @RequestParam long idCiudadDestino,
        @RequestParam long idDeposito
    ){
        //Controlar que no este vacio
        Contenedor contenedor = contenedorService
            .obtenerContenedor(idContenedor)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenedor no encontrado"));

        Ciudad ciudadOrigen = ciudadService
            .obtenerCiudad(idCiudadOrigen)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad origen no encontrada"));

        Ciudad ciudadDestino = ciudadService
            .obtenerCiudad(idCiudadDestino)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad destino no encontrada"));

        Deposito deposito = depositoService
            .obtenerDeposito(idDeposito)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Depósito no encontrado"));

        ResponseEntity<DistanciaDTO> distancia1 = obtenerDistancia(ciudadOrigen, deposito);
        ResponseEntity<DistanciaDTO> distancia2 = obtenerDistancia(ciudadDestino, deposito);
    
        if (!distancia1.getStatusCode().is2xxSuccessful() || !distancia2.getStatusCode().is2xxSuccessful()) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Error al obtener distancias");
        }

        DistanciaDTO d1 = distancia1.getBody();
        DistanciaDTO d2 = distancia2.getBody();

        //Controlar el calculo de las tarifas, pensar en hacer un metodo separado de calculo de tarifas
        TarifaBase tarifaBase = tarifaBaseService
            .obtenerTarifaBase(1)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));
        
        TarifaKM tarifaKM = tarifaKMService
            .obtenerTarifa(1)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));

        double monto = (d1.getKilometros() + d2.getKilometros()) * tarifaKM.getTarifa();

        String duracion = sumarDistancias(d1, d2);

        RespuestaCotizacionDTO respuesta = new RespuestaCotizacionDTO(monto, duracion);

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaPosteo> crearRutas(@RequestBody PostSolicitudDTO solicitudPost){

        Solicitud solicitud = solicitudService.obtenerSolicitud(solicitudPost.getIdSolicitud())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitud no encontrada"));
        
        Ciudad ciudadOrigen = ciudadService
            .obtenerCiudad(solicitudPost.getIdCiudadOrigen())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad origen no encontrada"));

        Ciudad ciudadDestino = ciudadService
            .obtenerCiudad(solicitudPost.getIdCiudadDestino())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad destino no encontrada"));

        Ciudad ciudadDeposito = ciudadService
            .obtenerCiudad(solicitudPost.getIdCiudadDeposito())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad de deposito no encontrada"));
        
        ResponseEntity<DistanciaDTO> distancia1 = obtenerDistancia(ciudadOrigen, deposito);
        ResponseEntity<DistanciaDTO> distancia2 = obtenerDistancia(ciudadDestino, deposito);
    
        if (!distancia1.getStatusCode().is2xxSuccessful() || !distancia2.getStatusCode().is2xxSuccessful()) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Error al obtener distancias");
        }

        DistanciaDTO d1 = distancia1.getBody();
        DistanciaDTO d2 = distancia2.getBody();
        
        Timestamp fechaSalida1 = new Timestamp(System.currentTimeMillis());
        Timestamp fechaLlegada1 = obtenerLlegada(fechaSalida1, d1);

        Timestamp fechaSalida2 = fechaLlegada1;
        Timestamp fechaLlegada2 = obtenerLlegada(fechaSalida2, d2);

        TramoRuta tramo1 = new TramoRuta(solicitud,1,"origen_deposito",ciudadOrigen,);
        
    }

    public Timestamp obtenerLlegada(Timestamp salida, DistanciaDTO d1){

        int horasSalida = obtenerHoras(d1);
        int minutosSalida = obtenerMinutos(d1);

        long tiempoAdicional1 = (horasSalida * 60 + minutosSalida) * 60 * 1000;

        return new Timestamp(salida.getTime() + tiempoAdicional1);
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

    public int obtenerHoras(DistanciaDTO d1){
        String[] horas1 = d1.getDuracionHoras().split(" ");
        return Integer.parseInt(horas1[0].trim());
    }

    public int obtenerMinutos(DistanciaDTO d1){
        String[] minutos1 = d1.getDuracionMinutos().split(" ");
        return Integer.parseInt(minutos1[0].trim());
    }

    //metodo que devuelve un tiempo total de horas y minutos a solamente horas
    public int pasajeAHoras(int horas, int minutos){
        int minutosParseados = minutos/60 * 100;
        return horas + minutosParseados;
    }

    public ResponseEntity<DistanciaDTO> obtenerDistancia(Ciudad ciudad, Deposito deposito){
        
        try{
            RestTemplate template = new RestTemplate();

            String origen = ciudad.getLatitud() + "," + ciudad.getLongitud();
            String destino = deposito.getLatitud() + "," + deposito.getLongitud();


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
}

