package com.tpi.pedidos.services;

import com.tpi.pedidos.dtos.CiudadDto;
import com.tpi.pedidos.dtos.CrearCiudadDto;
import com.tpi.pedidos.dtos.ActualizarCiudadDto;
import com.tpi.pedidos.exception.EntityNotFoundException;
import com.tpi.pedidos.entities.Ciudad;
import com.tpi.pedidos.repositories.CiudadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CiudadService {

    private final CiudadRepository repo;

    /**
     * Crea una nueva ciudad.
     */
    @Transactional
    public CiudadDto crear(CrearCiudadDto dto) {
        repo.findByNombre(dto.getNombre())
            .ifPresent(c -> { throw new IllegalArgumentException("Ya existe una ciudad con ese nombre"); });

        Ciudad entidad = new Ciudad();
        entidad.setNombre(dto.getNombre());
        entidad.setLatitud(dto.getLatitud());
        entidad.setLongitud(dto.getLongitud());

        Ciudad guardada = repo.save(entidad);
        return mapToDto(guardada);
    }

    /**
     * Actualiza una ciudad existente.
     */
    @Transactional
    public CiudadDto actualizar(Long id, ActualizarCiudadDto dto) {
        Ciudad entidad = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada con id " + id));

        entidad.setNombre(dto.getNombre());
        entidad.setLatitud(dto.getLatitud());
        entidad.setLongitud(dto.getLongitud());

        Ciudad actualizada = repo.save(entidad);
        return mapToDto(actualizada);
    }

    /**
     * Lista todas las ciudades.
     */
    @Transactional(readOnly = true)
    public List<CiudadDto> listar() {
        return repo.findAll()
                   .stream()
                   .map(this::mapToDto)
                   .collect(Collectors.toList());
    }

    /**
     * Obtiene una ciudad por su id.
     */
    @Transactional(readOnly = true)
    public CiudadDto obtenerPorId(Long id) {
        Ciudad entidad = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada con id " + id));
        return mapToDto(entidad);
    }

    // Borrar por id
    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Ciudad no encontrada con id " + id);
        }
        repo.deleteById(id);
    }

    // --- helpers privados de mapeo manual ---

    private CiudadDto mapToDto(Ciudad c) {
        return CiudadDto.builder()
                         .id(c.getId())
                         .nombre(c.getNombre())
                         .latitud(c.getLatitud())
                         .longitud(c.getLongitud())
                         .build();
    }
}
