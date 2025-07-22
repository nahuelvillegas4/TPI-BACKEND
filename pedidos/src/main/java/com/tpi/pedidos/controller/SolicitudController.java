package com.tpi.pedidos.controller;

import com.tpi.pedidos.dtos.*;
import com.tpi.pedidos.service.SolicitudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService service;

    @PostMapping
    public ResponseEntity<SolicitudDto> crear(@Valid @RequestBody CrearSolicitudDto dto) {
        SolicitudDto out = service.crear(dto);
        return ResponseEntity
            .created(URI.create("/pedidos/solicitudes/" + out.getId()))
            .body(out);
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDto> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody ActualizarSolicitudDto dto
    ) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /** Endpoint dedicado para asignar camión */
    @PutMapping("/{id}/asignar-camion/{camionId}")
    public ResponseEntity<SolicitudDto> asignarCamion(
        @PathVariable Long id,
        @PathVariable Long camionId
    ) {
        return ResponseEntity.ok(service.asignarCamion(id, camionId));
    }
}
