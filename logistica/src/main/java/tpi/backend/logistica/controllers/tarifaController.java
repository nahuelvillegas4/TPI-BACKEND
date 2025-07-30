package tpi.backend.logistica.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import feign.Response;
import tpi.backend.logistica.dtos.CrearTarifaDTO;
import tpi.backend.logistica.dtos.ModTarifaBaseDTO;
import tpi.backend.logistica.dtos.ModTarifaKMDTO;
import tpi.backend.logistica.dtos.TarifaBaseDTO;
import tpi.backend.logistica.dtos.tarifaKMDTO;
import tpi.backend.logistica.services.TarifaBaseService;
import tpi.backend.logistica.services.TarifaKMService;

@RestController
@RequestMapping("/tarifas")
public class tarifaController {
    
    private final TarifaKMService tarifaKMService;
    private final TarifaBaseService tarifaBaseService;

    public tarifaController(TarifaKMService tarifaKMService, TarifaBaseService tarifaBaseService){
        this.tarifaKMService = tarifaKMService;
        this.tarifaBaseService = tarifaBaseService;
    }

    @GetMapping("/obtenerBase/{id}")
    public ResponseEntity<TarifaBaseDTO> obtenerTarifa(@PathVariable Long id){
        TarifaBaseDTO respuesta = tarifaBaseService.obtenerTarifaBase(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/obtenerKM/{id}")
    public ResponseEntity<tarifaKMDTO> obtenerTarifaKM(@PathVariable Long id){
        tarifaKMDTO respuesta = tarifaKMService.obtenerTarifaKM(id);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/obtenerKM")
    public ResponseEntity<List<tarifaKMDTO>> obtenerTarifasKM(){
        List<tarifaKMDTO> respuesta = tarifaKMService.obtenerTarifasKMDTO();
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/crear")
    public ResponseEntity<tarifaKMDTO> crearTarifa(@RequestBody CrearTarifaDTO nuevaTarifaDTO){
        tarifaKMDTO respuesta = tarifaKMService.crearTarifaNueva(nuevaTarifaDTO);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/modBase/{id}")
    public ResponseEntity<TarifaBaseDTO> modificarTarifaBase(@PathVariable Long id, @RequestBody ModTarifaBaseDTO modificacion){
        TarifaBaseDTO respuesta = tarifaBaseService.modificarTarifa(id, modificacion);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/modKM/{id}")
    public ResponseEntity<tarifaKMDTO> modificarTarifaKM(@PathVariable Long id, @RequestBody ModTarifaKMDTO modificacion){
        tarifaKMDTO respuesta = tarifaKMService.modificarTarifaKM(id, modificacion);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/delKM/{id}")
    public ResponseEntity<String> eliminarTarifaKM(@PathVariable Long id) {
        tarifaKMService.eliminarTarifaKM(id);
        return ResponseEntity.ok("Tarifa eliminada correctamente");
    }


}
