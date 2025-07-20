package com.tpi.admin.controller;

import com.tpi.admin.dto.*;
import com.tpi.admin.service.CamionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/camiones")
@RequiredArgsConstructor
public class CamionController {

    private final CamionService service;

    /** POST /admin/camiones */
    @PostMapping
    public ResponseEntity<CamionDto> crear(@Valid @RequestBody CrearCamionDto dto) {
        CamionDto created = service.crear(dto);
        return ResponseEntity
            .created(URI.create("/admin/camiones/" + created.getId()))
            .body(created);
    }

    /** PUT /admin/camiones/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<CamionDto> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody ActualizarCamionDto dto
    ) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    /** GET /admin/camiones */
    @GetMapping
    public ResponseEntity<List<CamionDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    /** GET /admin/camiones/disponibles */
    @GetMapping("/disponibles")
    public ResponseEntity<List<CamionDto>> listarDisponibles() {
        return ResponseEntity.ok(service.listarDisponibles());
    }

    /** GET /admin/camiones/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<CamionDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    /** DELETE /admin/camiones/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
