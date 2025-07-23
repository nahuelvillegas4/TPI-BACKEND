package com.tpi.pedidos.service;

import com.tpi.pedidos.dtos.*;
import com.tpi.pedidos.entities.*;
import com.tpi.pedidos.repositories.*;
import com.tpi.pedidos.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class SolicitudService {

    private final SolicitudRepository repo;
    private final ContenedorRepository contRepo;
    private final CiudadRepository ciudadRepo;
    private final DepositoRepository depRepo;
    private final CamionRepository camRepo;

    @Transactional
    public SolicitudDto crear(CrearSolicitudDto dto) {
        Solicitud e = Solicitud.builder()
            .contenedor(contRepo.findById(dto.getContenedorId())
                .orElseThrow(() -> new EntityNotFoundException("Contenedor no encontrado")))
            .ciudadOrigen(ciudadRepo.findById(dto.getCiudadOrigenId())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad origen no encontrada")))
            .ciudadDestino(ciudadRepo.findById(dto.getCiudadDestinoId())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad destino no encontrada")))
            .deposito(depRepo.findById(dto.getDepositoId())
                .orElseThrow(() -> new EntityNotFoundException("Depósito no encontrado")))
            .costoEstimado(dto.getCostoEstimado())
            .tiempoEstimadoHoras(dto.getTiempoEstimadoHoras())
            .build();

        if (dto.getCamionId() != null) {
            e.setCamion(camRepo.findById(dto.getCamionId())
                .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado")));
        }

        return map(repo.save(e));
    }

    @Transactional(readOnly = true)
    public List<SolicitudDto> listar() {
        return repo.findAll()
                   .stream()
                   .map(this::map)
                   .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SolicitudDto obtener(Long id) {
        return repo.findById(id)
                   .map(this::map)
                   .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada: " + id));
    }

    @Transactional
    public SolicitudDto actualizar(Long id, ActualizarSolicitudDto dto) {
        Solicitud e = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada: " + id));
        e.setCostoEstimado(dto.getCostoEstimado());
        e.setTiempoEstimadoHoras(dto.getTiempoEstimadoHoras());
        return map(repo.save(e));
    }

    /** Asigna un camión después de creada la solicitud */
    @Transactional
    public SolicitudDto asignarCamion(Long solicitudId, Long camionId) {
        Solicitud e = repo.findById(solicitudId)
            .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada"));
        Camion c = camRepo.findById(camionId)
            .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado"));
        e.setCamion(c);
        return map(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id))
            throw new EntityNotFoundException("Solicitud no encontrada: " + id);
        repo.deleteById(id);
    }

    private SolicitudDto map(Solicitud e) {
        return SolicitudDto.builder()
            .id(e.getId())
            .contenedorId(e.getContenedor().getId())
            .ciudadOrigenId(e.getCiudadOrigen().getId())
            .ciudadOrigenNombre(e.getCiudadOrigen().getNombre())
            .ciudadDestinoId(e.getCiudadDestino().getId())
            .ciudadDestinoNombre(e.getCiudadDestino().getNombre())
            .depositoId(e.getDeposito().getId())
            .depositoDireccion(e.getDeposito().getDireccion())
            .camionId(e.getCamion() != null ? e.getCamion().getId() : null)
            .costoEstimado(e.getCostoEstimado())
            .tiempoEstimadoHoras(e.getTiempoEstimadoHoras())
            .build();
    }
}
