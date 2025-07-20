package com.tpi.admin.services;

import com.tpi.admin.dtos.*;
import com.tpi.admin.exception.EntityNotFoundException;
import com.tpi.admin.entities.Camion;
import com.tpi.admin.repositories.CamionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class CamionService {

    private final CamionRepository repo;

    @Transactional
    public CamionDto crear(CrearCamionDto dto) {
        Camion e = Camion.builder()
            .capacidadPeso(dto.getCapacidadPeso())
            .capacidadVolumen(dto.getCapacidadVolumen())
            .disponible(dto.getDisponible())
            .build();
        Camion saved = repo.save(e);
        return toDto(saved);
    }

    @Transactional
    public CamionDto actualizar(Long id, ActualizarCamionDto dto) {
        Camion e = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado con id " + id));
        e.setCapacidadPeso(dto.getCapacidadPeso());
        e.setCapacidadVolumen(dto.getCapacidadVolumen());
        e.setDisponible(dto.getDisponible());
        Camion updated = repo.save(e);
        return toDto(updated);
    }

    @Transactional(readOnly = true)
    public List<CamionDto> listarTodos() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CamionDto> listarDisponibles() {
        return repo.findByDisponibleTrue().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CamionDto obtenerPorId(Long id) {
        Camion e = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado con id " + id));
        return toDto(e);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Camión no encontrado con id " + id);
        }
        repo.deleteById(id);
    }

    private CamionDto toDto(Camion e) {
        return CamionDto.builder()
            .id(e.getId())
            .capacidadPeso(e.getCapacidadPeso())
            .capacidadVolumen(e.getCapacidadVolumen())
            .disponible(e.getDisponible())
            .build();
    }
}
