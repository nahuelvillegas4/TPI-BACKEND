package com.tpi.pedidos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tpi.pedidos.dtos.CamionDto;
import com.tpi.pedidos.dtos.ClienteDto;
import com.tpi.pedidos.entities.Camion;
import com.tpi.pedidos.entities.Cliente;
import com.tpi.pedidos.exception.EntityNotFoundException;
import com.tpi.pedidos.repositories.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository repo;

    @Transactional(readOnly = true)
    public List<ClienteDto> listarTodos() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteDto obtenerPorId(Long id) {
        Cliente e = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id " + id));
        return toDto(e);
    }

    
    private ClienteDto toDto(com.tpi.pedidos.entities.Cliente cliente) {
    return ClienteDto.builder()
            .id(cliente.getId())
            .nombre(cliente.getNombre())
            .email(cliente.getEmail())
            .build();
    }


}


