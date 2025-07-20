package com.tpi.admin.controller;

import com.tpi.admin.dtos.CrearTarifaKmDto;
import com.tpi.admin.dtos.TarifaKmDto;
import com.tpi.admin.services.TarifaKmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/tarifas/km")
@RequiredArgsConstructor
public class TarifaKmController {

    private final TarifaKmService service;

    @PostMapping
    public ResponseEntity<TarifaKmDto> crear(@Valid @RequestBody CrearTarifaKmDto dto) {
        TarifaKmDto created = service.crear(dto);
        return ResponseEntity
            .created(URI.create("/admin/tarifas/km/" + created.getId()))
            .body(created);
    }

    @GetMapping
    public ResponseEntity<List<TarifaKmDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarifaKmDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarifaKmDto> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody CrearTarifaKmDto dto
    ) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
