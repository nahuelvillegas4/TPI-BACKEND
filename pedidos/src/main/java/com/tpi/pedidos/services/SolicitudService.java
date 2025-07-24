package com.tpi.pedidos.services;

import com.tpi.pedidos.dtos.*;
import com.tpi.pedidos.entities.*;
import com.tpi.pedidos.repositories.*;
import com.tpi.pedidos.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SolicitudService {

    private final SolicitudRepository repo;
    private final ContenedorRepository contRepo;
    private final CiudadRepository ciudadRepo;
    private final DepositoRepository depRepo;
    private final CamionRepository camRepo;
    private final RestTemplate restTemplate;

    @Value("${planificacion.url}")
    private String planificacionUrl;

    @Transactional
    public SolicitudDto crear(CrearSolicitudDto dto) {
        // 1) Construyo y guardo la entidad sin costos
        Solicitud e = Solicitud.builder()
            .contenedor(contRepo.findById(dto.getContenedorId())
                .orElseThrow(() -> new EntityNotFoundException("Contenedor no encontrado")))
            .ciudadOrigen(ciudadRepo.findById(dto.getCiudadOrigenId())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad origen no encontrada")))
            .ciudadDestino(ciudadRepo.findById(dto.getCiudadDestinoId())
                .orElseThrow(() -> new EntityNotFoundException("Ciudad destino no encontrada")))
            .deposito(depRepo.findById(dto.getDepositoId())
                .orElseThrow(() -> new EntityNotFoundException("Depósito no encontrado")))
            .build();

        if (dto.getCamionId() != null) {
            e.setCamion(camRepo.findById(dto.getCamionId())
                .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado")));
        }

        e = repo.save(e);

        // 2) Llamo al microservicio de planificación para obtener costo y tiempo
        PlanificacionRequestDto req = new PlanificacionRequestDto(
            e.getId(),
            e.getCiudadOrigen().getId(),
            e.getCiudadDestino().getId(),
            e.getDeposito().getId()
        );


        ResponseEntity<PlanificacionResponseDto> res = restTemplate.postForEntity(
            planificacionUrl,
            req,
            PlanificacionResponseDto.class
        );

        if (!res.getStatusCode().is2xxSuccessful() || res.getBody() == null) {
            throw new IllegalStateException("No se pudo calcular costo/tiempo desde el servicio de planificación");
        }

        log.debug("Llamo a depósito con id={}", res.getBody());

        // 3) Seteo los valores retornados y guardo de nuevo
        //e.setCostoEstimado(res.getBody().getCostoEstimado());
        //e.setTiempoEstimadoHoras(res.getBody().getTiempoEstimadoHoras());
        e = repo.save(e);

        return map(e);
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
            .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada: " + solicitudId));
        Camion c = camRepo.findById(camionId)
            .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado: " + camionId));
        e.setCamion(c);
        return map(repo.save(e));
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Solicitud no encontrada: " + id);
        }
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
