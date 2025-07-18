package tpi.backend.logistica.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.extern.slf4j.Slf4j;
import tpi.backend.logistica.dtos.CotizacionSolicitudDTO;
import tpi.backend.logistica.dtos.DistanciaDTO;
import tpi.backend.logistica.dtos.RespuestaCotizacionDTO;
import tpi.backend.logistica.entities.Ciudad;
import tpi.backend.logistica.entities.Contenedor;
import tpi.backend.logistica.entities.Deposito;
import tpi.backend.logistica.entities.TarifaBase;
import tpi.backend.logistica.entities.TarifaKM;
import tpi.backend.logistica.services.CiudadService;
import tpi.backend.logistica.services.ContenedorService;
import tpi.backend.logistica.services.DepositoService;
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

    public SolicitudControler(ContenedorService contenedorService, CiudadService ciudadService, DepositoService depositoService,
        TarifaBaseService tarifaBaseService, TarifaKMService tarifaKMService){
        this.contenedorService = contenedorService;
        this.ciudadService = ciudadService;
        this.depositoService = depositoService;
        this.tarifaBaseService = tarifaBaseService;
        this.tarifaKMService = tarifaKMService;
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


        TarifaBase tarifaBase = tarifaBaseService
            .obtenerTarifaBase(1)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));
        
        TarifaKM tarifaKM = tarifaKMService
            .obtenerTarifa(1)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));

        double monto = (d1.getKilometros() + d2.getKilometros()) * tarifaKM.getTarifa();
        String duracion = d1.getDuracionTexto() + " " + d2.getDuracionTexto();

        RespuestaCotizacionDTO respuesta = new RespuestaCotizacionDTO(monto, duracion);

        return ResponseEntity.ok(respuesta);

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

