package com.tpi.pedidos.services;

import com.tpi.pedidos.dtos.CrearTarifaKmDto;
import com.tpi.pedidos.dtos.TarifaKmDto;
import com.tpi.pedidos.entities.TarifaKM;
import com.tpi.pedidos.exception.EntityNotFoundException;
import com.tpi.pedidos.repositories.TarifaKmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarifaKmService {

    private final TarifaKmRepository repo;

    @Transactional
    public TarifaKmDto crear(CrearTarifaKmDto dto) {
        TarifaKM e = new TarifaKM();
        e.setTarifa(dto.getTarifa());
        TarifaKM saved = repo.save(e);
        return map(saved);
    }

    @Transactional(readOnly = true)
    public List<TarifaKmDto> listar() {
        return repo.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TarifaKmDto obtener(Long id) {
        return repo.findById(id)
            .map(this::map)
            .orElseThrow(() -> new EntityNotFoundException("TarifaKM no encontrada: " + id));
    }

    @Transactional
    public TarifaKmDto actualizar(Long id, CrearTarifaKmDto dto) {
        TarifaKM e = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("TarifaKM no encontrada: " + id));
        e.setTarifa(dto.getTarifa());
        return map(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("TarifaKM no encontrada: " + id);
        }
        repo.deleteById(id);
    }

    private TarifaKmDto map(TarifaKM e) {
        return TarifaKmDto.builder()
            .id(e.getId())
            .tarifa(e.getTarifa())
            .build();
    }
}
