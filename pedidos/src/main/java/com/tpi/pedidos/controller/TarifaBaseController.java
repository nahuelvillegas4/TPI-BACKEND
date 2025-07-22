package com.tpi.pedidos.controller;

import com.tpi.pedidos.dtos.CrearTarifaBaseDto;
import com.tpi.pedidos.dtos.TarifaBaseDto;
import com.tpi.pedidos.services.TarifaBaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos/tarifas/base")
@RequiredArgsConstructor
public class TarifaBaseController {

    private final TarifaBaseService service;

    @PostMapping
    public ResponseEntity<TarifaBaseDto> crear(@Valid @RequestBody CrearTarifaBaseDto dto) {
        TarifaBaseDto created = service.crear(dto);
        return ResponseEntity
            .created(URI.create("/pedidos/tarifas/base/" + created.getId()))
            .body(created);
    }

    @GetMapping
    public ResponseEntity<List<TarifaBaseDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarifaBaseDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarifaBaseDto> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody CrearTarifaBaseDto dto
    ) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
