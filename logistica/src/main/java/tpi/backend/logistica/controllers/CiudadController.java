package tpi.backend.logistica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import tpi.backend.logistica.entities.Ciudad;
import tpi.backend.logistica.services.CiudadService;

import java.util.List;

@RestController
@RequestMapping("/logistica/ciudades")
public class CiudadController {

    private final CiudadService ciudadService;

    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping
    public List<Ciudad> getAllCiudades() {
        return ciudadService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> getCiudad(@PathVariable Long id) {
        return ciudadService.obtenerCiudad(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ciudad> createCiudad(@Valid @RequestBody Ciudad ciudad) {
        Ciudad nuevaCiudad = ciudadService.registrarCiudad(ciudad);
        return ResponseEntity.status(201).body(nuevaCiudad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> updateCiudad(@PathVariable Long id, @Valid @RequestBody Ciudad ciudad) {
        Ciudad ciudadActualizada = ciudadService.actualizarCiudad(id, ciudad);
        return ResponseEntity.ok(ciudadActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCiudad(@PathVariable Long id) {
        ciudadService.eliminarCiudad(id);
        return ResponseEntity.noContent().build();
    }

    // Manejo de RuntimeException para devolver 404 y mensaje de error
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
