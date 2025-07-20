package com.tpi.admin.services;

import com.tpi.admin.dtos.DepositoDto;
import com.tpi.admin.dtos.CrearDepositoDto;
import com.tpi.admin.dtos.ActualizarDepositoDto;
import com.tpi.admin.exception.EntityNotFoundException;
import com.tpi.admin.entities.Deposito;
import com.tpi.admin.entities.Ciudad;
import com.tpi.admin.repositories.DepositoRepository;
import com.tpi.admin.repositories.CiudadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepositoService {

    private final DepositoRepository repo;
    private final CiudadRepository ciudadRepo;

    @Transactional
    public DepositoDto crear(CrearDepositoDto dto) {
        // 1. Verificar que exista la ciudad
        Ciudad ciudad = ciudadRepo.findById(dto.getCiudadId())
            .orElseThrow(() -> new EntityNotFoundException(
                "Ciudad no encontrada con id " + dto.getCiudadId()));

        // 2. Construir entidad
        Deposito entidad = Deposito.builder()
            .direccion(dto.getDireccion())
            .ciudad(ciudad)
            .latitud(dto.getLatitud())
            .longitud(dto.getLongitud())
            .build();

        // 3. Guardar
        Deposito guardado = repo.save(entidad);
        return mapToDto(guardado);
    }

    @Transactional
    public DepositoDto actualizar(Long id, ActualizarDepositoDto dto) {
        // 1. Buscar depósito existente
        Deposito entidad = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Depósito no encontrado con id " + id));

        // 2. Verificar ciudad nueva (o misma)
        Ciudad ciudad = ciudadRepo.findById(dto.getCiudadId())
            .orElseThrow(() -> new EntityNotFoundException(
                "Ciudad no encontrada con id " + dto.getCiudadId()));

        // 3. Actualizar campos
        entidad.setDireccion(dto.getDireccion());
        entidad.setCiudad(ciudad);
        entidad.setLatitud(dto.getLatitud());
        entidad.setLongitud(dto.getLongitud());

        // 4. Guardar cambios
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
        // verifica existencia de ciudad primero
        if (!ciudadRepo.existsById(ciudadId)) {
            throw new EntityNotFoundException("Ciudad no encontrada con id " + ciudadId);
        }
        return repo.findByCiudadId(ciudadId)
                   .stream()
                   .map(this::mapToDto)
                   .collect(Collectors.toList());
    }

    // Borrar por id
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
            .ciudadId(d.getCiudad().getId())
            .latitud(d.getLatitud())
            .longitud(d.getLongitud())
            .build();
    }
}
