package com.tpi.pedidos.services;

import com.tpi.pedidos.dtos.CrearTarifaBaseDto;
import com.tpi.pedidos.dtos.TarifaBaseDto;
import com.tpi.pedidos.entities.TarifaBase;
import com.tpi.pedidos.exception.EntityNotFoundException;
import com.tpi.pedidos.repositories.TarifaBaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarifaBaseService {

    private final TarifaBaseRepository repo;

    @Transactional
    public TarifaBaseDto crear(CrearTarifaBaseDto dto) {
        TarifaBase e = new TarifaBase();
        e.setTarifa(dto.getTarifa());
        TarifaBase saved = repo.save(e);
        return map(saved);
    }

    @Transactional(readOnly = true)
    public List<TarifaBaseDto> listar() {
        return repo.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TarifaBaseDto obtener(Long id) {
        return repo.findById(id)
            .map(this::map)
            .orElseThrow(() -> new EntityNotFoundException("TarifaBase no encontrada: " + id));
    }

    @Transactional
    public TarifaBaseDto actualizar(Long id, CrearTarifaBaseDto dto) {
        TarifaBase e = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("TarifaBase no encontrada: " + id));
        e.setTarifa(dto.getTarifa());
        return map(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("TarifaBase no encontrada: " + id);
        }
        repo.deleteById(id);
    }

    private TarifaBaseDto map(TarifaBase e) {
        return TarifaBaseDto.builder()
            .id(e.getId())
            .tarifa(e.getTarifa())
            .build();
    }
}
