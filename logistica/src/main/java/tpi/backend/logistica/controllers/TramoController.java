package tpi.backend.logistica.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import tpi.backend.logistica.dtos.ActualizarTramoDTO;
import tpi.backend.logistica.dtos.CrearTarifaDTO;
import tpi.backend.logistica.dtos.DatosRespuestaActualizacionDTO;
import tpi.backend.logistica.dtos.Tramo_rutaDTO;
import tpi.backend.logistica.dtos.tarifaKMDTO;
import tpi.backend.logistica.services.TramoService;

@RestController
@RequestMapping("/logistica/tramos")
public class TramoController {
    
    private final TramoService tramoService;

    public TramoController(TramoService tramoService){
        this.tramoService = tramoService;
    }

    @PostMapping("/modificar")
    public ResponseEntity<DatosRespuestaActualizacionDTO> actualizarTramos(@RequestBody ActualizarTramoDTO nuevoTramoDTO){
        DatosRespuestaActualizacionDTO respuesta = tramoService.actualizarTramo(nuevoTramoDTO);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<Tramo_rutaDTO>> obtenerTramos(){
        List<Tramo_rutaDTO> respuesta = tramoService.obtenerTramos();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<List<Tramo_rutaDTO>> obtenerTramo(@PathVariable Long id){
        List<Tramo_rutaDTO> respuesta = tramoService.obtenerTramo(id);
        return ResponseEntity.ok(respuesta);
    }
}
