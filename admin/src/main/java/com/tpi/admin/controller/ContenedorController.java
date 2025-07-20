package com.tpi.admin.controller;

import com.tpi.admin.dtos.ContenedorDto;
import com.tpi.admin.dtos.CrearContenedorDto;
import com.tpi.admin.dtos.ActualizarContenedorDto;
import com.tpi.admin.services.ContenedorService;
import com.tpi.admin.entities.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/contenedores")
@RequiredArgsConstructor
public class ContenedorController {

    private final ContenedorService service;

    /**
     * Crea un nuevo contenedor.
     * POST /admin/contenedores
     */
    @PostMapping
    public ResponseEntity<ContenedorDto> crear(
        @Valid @RequestBody CrearContenedorDto dto
    ) {
        ContenedorDto created = service.crear(dto);
        return ResponseEntity
            .created(URI.create("/admin/contenedores/" + created.getId()))
            .body(created);
    }

    /**
     * Actualiza un contenedor existente.
     * PUT /admin/contenedores/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContenedorDto> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody ActualizarContenedorDto dto
    ) {
        ContenedorDto updated = service.actualizar(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Lista todos los contenedores.
     * GET /admin/contenedores
     */
    @GetMapping
    public ResponseEntity<List<ContenedorDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    /**
     * Obtiene un solo contenedor por su ID.
     * GET /admin/contenedores/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContenedorDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    /**
     * Filtra contenedores por estado.
     * GET /admin/contenedores/estado/{estado}
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ContenedorDto>> listarPorEstado(@PathVariable Estado estado) {
        return ResponseEntity.ok(service.listarPorEstado(estado));
    }

    /**
     * Elimina un contenedor.
     * DELETE /admin/contenedores/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
