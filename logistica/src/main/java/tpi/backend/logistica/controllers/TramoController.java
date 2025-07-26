package tpi.backend.logistica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import tpi.backend.logistica.dtos.ActualizarTramoDTO;
import tpi.backend.logistica.dtos.DatosRespuestaActualizacionDTO;
import tpi.backend.logistica.services.TramoService;

@RestController
@RequestMapping("/planificacion")
public class TramoController {
    
    private final TramoService tramoService;

    public TramoController(TramoService tramoService){
        this.tramoService = tramoService;
    }

    @PutMapping("/modificar")
    public ResponseEntity<DatosRespuestaActualizacionDTO> actualizarTramos(@RequestBody ActualizarTramoDTO nuevoTramoDTO){
        DatosRespuestaActualizacionDTO respuesta = tramoService.actualizarTramo(nuevoTramoDTO);
        return ResponseEntity.ok(respuesta);
    }
}
