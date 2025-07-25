package com.tpi.pedidos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.pedidos.dtos.ClienteDto;
import com.tpi.pedidos.services.ClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos/clientes")
@RequiredArgsConstructor

public class ClienteController {
    
    private final ClienteService service;
    
    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
}
