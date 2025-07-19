package com.tpi.admin.controller;

import com.tpi.admin.dto.CiudadDto;
import com.tpi.admin.dto.CrearCiudadDto;
import com.tpi.admin.dto.ActualizarCiudadDto;
import com.tpi.admin.service.CiudadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/ciudades")
@RequiredArgsConstructor
@Validated
public class CiudadController {

    private final CiudadService ciudadService;

    /**
     * Crea una nueva ciudad.
     * - POST /admin/ciudades
     */
    @PostMapping
    public ResponseEntity<CiudadDto> crearCiudad(
            @Valid @RequestBody CrearCiudadDto dto
    ) {
        CiudadDto created = ciudadService.crear(dto);
        // Retornar 201 Created con Location al recurso
        return ResponseEntity
                .created(URI.create("/admin/ciudades/" + created.getId()))
                .body(created);
    }

    /**
     * Actualiza los datos de una ciudad existente.
     * - PUT /admin/ciudades/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<CiudadDto> actualizarCiudad(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarCiudadDto dto
    ) {
        CiudadDto updated = ciudadService.actualizar(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Lista todas las ciudades.
     * - GET /admin/ciudades
     */
    @GetMapping
    public ResponseEntity<List<CiudadDto>> listarCiudades() {
        List<CiudadDto> all = ciudadService.listar();
        return ResponseEntity.ok(all);
    }

    /**
     * Obtiene una ciudad por su id.
     * - GET /admin/ciudades/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<CiudadDto> obtenerCiudad(
            @PathVariable Long id
    ) {
        CiudadDto dto = ciudadService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }
}
