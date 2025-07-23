package tpi.backend.logistica.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/camion")
// public class CamionControler {

//     private final CamionService camionService;

//     public CamionControler(CamionService camionService){
//         this.camionService = camionService;
//     }

//     @GetMapping
//     public ResponseEntity<List<CamionDTO>> obtenerDisponibles(){
//         List<CamionDTO> listaCamiones = camionService.obtenerDisponibles().stream()
//             .map(camionService::transformarDTO)
//             .toList();
//         return ResponseEntity.ok(listaCamiones);
//     }
    
// }
