package tpi.backend.logistica.controllers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import tpi.backend.logistica.dtos.DatosRespuestaPosteo;
import tpi.backend.logistica.dtos.PostSolicitudDTO;
import tpi.backend.logistica.dtos.DepositoDTO;
import tpi.backend.logistica.dtos.RespuestaCotizacionDTO;
import tpi.backend.logistica.entities.Ciudad;
import tpi.backend.logistica.services.CiudadService;
import tpi.backend.logistica.services.SolicitudService;
import tpi.backend.logistica.services.TarifaBaseService;
import tpi.backend.logistica.services.TarifaKMService;
import tpi.backend.logistica.services.TramoService;
import tpi.backend.logistica.clients.DepositoClient;

@RestController
@Slf4j
@RequestMapping("/planificacion")
public class SolicitudControler {

    @Value("${maps.service.url}")
    private String mapsServiceUrl;

    private final CiudadService ciudadService;
    private final SolicitudService solicitudService;
    private final HttpServletRequest request;
    private final DepositoClient depositoClient;

    public SolicitudControler(CiudadService ciudadService,
        TarifaBaseService tarifaBaseService, TarifaKMService tarifaKMService, DepositoClient depositoClient, SolicitudService solicitudService, TramoService tramoService, HttpServletRequest request){
        this.ciudadService = ciudadService;
        this.solicitudService = solicitudService;
        this.request = request;
        this.depositoClient = depositoClient;
    }

    //ResponseEntity<RespuestaCotizacionDTO>
    @GetMapping 
    public ResponseEntity<RespuestaCotizacionDTO> cotizarSolicitud(
        @RequestParam double PesoContenedor,
        @RequestParam double VolumenContenedor,
        @RequestParam long idCiudadOrigen,
        @RequestParam long idCiudadDestino,
        @RequestParam double latitudDeposito,
        @RequestParam double longitudDeposito
    ){

        Ciudad ciudadOrigen = ciudadService
            .obtenerCiudad(idCiudadOrigen)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad origen no encontrada"));

        Ciudad ciudadDestino = ciudadService
            .obtenerCiudad(idCiudadDestino)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad destino no encontrada"));

        RespuestaCotizacionDTO respuesta = solicitudService.cotizarSolicitud(PesoContenedor, VolumenContenedor, ciudadOrigen, ciudadDestino, 
                            latitudDeposito, longitudDeposito).getBody();


        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/crear")
    public ResponseEntity<DatosRespuestaPosteo> crearRutas(@RequestBody PostSolicitudDTO solicitudPost) {
        // 2. Invocamos al servicio de depósitos usando el idCity correcto
        DepositoDTO deposito = depositoClient
            .getDepositoById(solicitudPost.getIdCiudadDeposito());  // ② getIdCiudadDeposito()

        // 3. Usamos el id de ciudad que nos devolvió el depósito
        Ciudad ciudadDeposito = ciudadService
            .obtenerCiudad(deposito.getIdCiudad())                // ③ deposito.getIdCiudad()
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Ciudad del depósito no encontrada"));

        // 4. Delegamos al service pasándole la entidad ciudadDeposito
        DatosRespuestaPosteo resultado = solicitudService
            .crearRutas(solicitudPost, ciudadDeposito)             // ahora recibe Ciudad
            .getBody();

        return ResponseEntity.ok(resultado);
    }
}

