package com.tpi.pedidos.controller;

import com.tpi.pedidos.dtos.*;
import com.tpi.pedidos.services.CamionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos/camiones")
@RequiredArgsConstructor
public class CamionController {

    private final CamionService service;

    // POST /pedidos/camiones
    @PostMapping
    public ResponseEntity<CamionDto> crear(@Valid @RequestBody CrearCamionDto dto) {
        CamionDto created = service.crear(dto);
        return ResponseEntity
            .created(URI.create("/pedidos/camiones/" + created.getId()))
            .body(created);
    }

    // PUT /pedidos/camiones/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CamionDto> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody ActualizarCamionDto dto
    ) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    /** GET /pedidos/camiones */
    @GetMapping
    public ResponseEntity<List<CamionDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    /** GET /pedidos/camiones/disponibles */
    @GetMapping("/disponibles")
    public ResponseEntity<List<CamionDto>> listarDisponibles() {
        return ResponseEntity.ok(service.listarDisponibles());
    }

    /** GET /pedidos/camiones/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<CamionDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    /** DELETE /pedidos/camiones/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
