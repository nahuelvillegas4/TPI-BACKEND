package com.tpi.pedidos.services;

import com.tpi.pedidos.dtos.DepositoDto;
import com.tpi.pedidos.dtos.CrearDepositoDto;
import com.tpi.pedidos.dtos.ActualizarDepositoDto;
import com.tpi.pedidos.exception.EntityNotFoundException;
import com.tpi.pedidos.entities.Deposito;
import com.tpi.pedidos.repositories.DepositoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tpi.pedidos.client.CiudadServiceClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepositoService {

    private final DepositoRepository repo;
    private final CiudadServiceClient ciudadServiceClient; 

    @Transactional
    public DepositoDto crear(CrearDepositoDto dto) {
        if (!ciudadServiceClient.ciudadExiste(dto.getCiudadId())) {
            throw new EntityNotFoundException("Ciudad no encontrada con id " + dto.getCiudadId());
        }
        Deposito entidad = Deposito.builder()
            .direccion(dto.getDireccion())
            .ciudadId(dto.getCiudadId())
            .latitud(dto.getLatitud())
            .longitud(dto.getLongitud())
            .build();
        Deposito guardado = repo.save(entidad);
        return mapToDto(guardado);
    }

    @Transactional
    public DepositoDto actualizar(Long id, ActualizarDepositoDto dto) {
        Deposito entidad = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Depósito no encontrado con id " + id));
        if (!ciudadServiceClient.ciudadExiste(dto.getCiudadId())) {
            throw new EntityNotFoundException("Ciudad no encontrada con id " + dto.getCiudadId());
        }
        entidad.setDireccion(dto.getDireccion());
        entidad.setCiudadId(dto.getCiudadId());
        entidad.setLatitud(dto.getLatitud());
        entidad.setLongitud(dto.getLongitud());
        Deposito actualizado = repo.save(entidad);
        return mapToDto(actualizado);
    }

    @Transactional(readOnly = true)
    public List<DepositoDto> listar() {
        return repo.findAll()
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DepositoDto obtenerPorId(Long id) {
        Deposito entidad = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Depósito no encontrado con id " + id));
        return mapToDto(entidad);
    }

    @Transactional(readOnly = true)
    public List<DepositoDto> listarPorCiudad(Long ciudadId) {
        if (!ciudadServiceClient.ciudadExiste(ciudadId)) {
            throw new EntityNotFoundException("Ciudad no encontrada con id " + ciudadId);
        }
        return repo.findByCiudadId(ciudadId)
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Depósito no encontrado con id " + id);
        }
        repo.deleteById(id);
    }

    // --- Helper de conversión ---
    private DepositoDto mapToDto(Deposito d) {
        return DepositoDto.builder()
            .id(d.getId())
            .direccion(d.getDireccion())
            .ciudadId(d.getCiudadId())
            .latitud(d.getLatitud())
            .longitud(d.getLongitud())
            .build();
    }
}
