package com.tpi.pedidos.controller;

import com.tpi.pedidos.dtos.ContenedorDto;
import com.tpi.pedidos.dtos.CrearContenedorDto;
import com.tpi.pedidos.dtos.ActualizarContenedorDto;
import com.tpi.pedidos.services.ContenedorService;
import com.tpi.pedidos.entities.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos/contenedores")
@RequiredArgsConstructor
public class ContenedorController {

    private final ContenedorService service;

    /**
     * Crea un nuevo contenedor.
     * POST /pedidos/contenedores
     */
    @PostMapping
    public ResponseEntity<ContenedorDto> crear(
        @Valid @RequestBody CrearContenedorDto dto
    ) {
        ContenedorDto created = service.crear(dto);
        return ResponseEntity
            .created(URI.create("/pedidos/contenedores/" + created.getId()))
            .body(created);
    }

    /**
     * Actualiza un contenedor existente.
     * PUT /pedidos/contenedores/{id}
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
     * GET /pedidos/contenedores
     */
    @GetMapping
    public ResponseEntity<List<ContenedorDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    /**
     * Obtiene un solo contenedor por su ID.
     * GET /pedidos/contenedores/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContenedorDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    /**
     * Filtra contenedores por estado.
     * GET /pedidos/contenedores/estado/{estado}
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ContenedorDto>> listarPorEstado(@PathVariable Estado estado) {
        return ResponseEntity.ok(service.listarPorEstado(estado));
    }

    /**
     * Elimina un contenedor.
     * DELETE /pedidos/contenedores/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
