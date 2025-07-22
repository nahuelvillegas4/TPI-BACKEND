package com.tpi.pedidos.controller;

import com.tpi.pedidos.dtos.DepositoDto;
import com.tpi.pedidos.dtos.CrearDepositoDto;
import com.tpi.pedidos.dtos.ActualizarDepositoDto;
import com.tpi.pedidos.services.DepositoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos/depositos")
@RequiredArgsConstructor
public class DepositoController {

    private final DepositoService service;

    /** POST /pedidos/depositos */
    @PostMapping
    public ResponseEntity<DepositoDto> crear(
        @Valid @RequestBody CrearDepositoDto dto
    ) {
        DepositoDto created = service.crear(dto);
        return ResponseEntity
            .created(URI.create("/pedidos/depositos/" + created.getId()))
            .body(created);
    }

    /** PUT /pedidos/depositos/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<DepositoDto> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody ActualizarDepositoDto dto
    ) {
        DepositoDto updated = service.actualizar(id, dto);
        return ResponseEntity.ok(updated);
    }

    /** GET /pedidos/depositos */
    @GetMapping
    public ResponseEntity<List<DepositoDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    /** GET /pedidos/depositos/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<DepositoDto> obtenerPorId(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    /** GET /pedidos/depositos/ciudad/{ciudadId} */
    @GetMapping("/ciudad/{ciudadId}")
    public ResponseEntity<List<DepositoDto>> listarPorCiudad(
        @PathVariable Long ciudadId
    ) {
        return ResponseEntity.ok(service.listarPorCiudad(ciudadId));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDeposito(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }

}
