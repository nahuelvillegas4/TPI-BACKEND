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
import com.tpi.pedidos.client.CiudadServiceClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SolicitudService {

    private final SolicitudRepository repo;
    private final ContenedorRepository contRepo;
    private final DepositoRepository depRepo;
    private final CamionRepository camRepo;
    private final CiudadServiceClient ciudadServiceClient; // El client HTTP
    private final RestTemplate restTemplate;

    @Value("${planificacion.url}")
    private String planificacionUrl;


    @Transactional
    public SolicitudDto crear(CrearSolicitudDto dto) {
        // Validar existencia de las ciudades por microservicio logística
        if (!ciudadServiceClient.ciudadExiste(dto.getCiudadOrigenId())) {
            throw new EntityNotFoundException("Ciudad origen no encontrada con id " + dto.getCiudadOrigenId());
        }
        if (!ciudadServiceClient.ciudadExiste(dto.getCiudadDestinoId())) {
            throw new EntityNotFoundException("Ciudad destino no encontrada con id " + dto.getCiudadDestinoId());
        }
        // El resto sigue igual
        Solicitud e = Solicitud.builder()
            .contenedor(contRepo.findById(dto.getContenedorId())
                .orElseThrow(() -> new EntityNotFoundException("Contenedor no encontrado")))
            .ciudadOrigenId(dto.getCiudadOrigenId())
            .ciudadDestinoId(dto.getCiudadDestinoId())
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
            e.getCiudadOrigenId(),
            e.getCiudadDestinoId(),
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
        // Si en actualizar también permitís cambiar origen/destino, agregá validación:
        /*
        if (dto.getCiudadOrigenId() != null && !ciudadServiceClient.ciudadExiste(dto.getCiudadOrigenId())) {
            throw new EntityNotFoundException("Ciudad origen no encontrada con id " + dto.getCiudadOrigenId());
        }
        if (dto.getCiudadDestinoId() != null && !ciudadServiceClient.ciudadExiste(dto.getCiudadDestinoId())) {
            throw new EntityNotFoundException("Ciudad destino no encontrada con id " + dto.getCiudadDestinoId());
        }
        */
        e.setCostoEstimado(dto.getCostoEstimado());
        e.setTiempoEstimadoHoras(dto.getTiempoEstimadoHoras());
        return map(repo.save(e));
    }

    /** Asigna un camión después de creada la solicitud */
    @Transactional
    public SolicitudDto asignarCamion(Long solicitudId, Long camionId) {
    Solicitud solicitud = repo.findById(solicitudId)
        .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada: " + solicitudId));
    Camion camion = camRepo.findById(camionId)
        .orElseThrow(() -> new EntityNotFoundException("Camión no encontrado: " + camionId));

    // --- NUEVA VALIDACIÓN ---
    Double pesoContenedor   = solicitud.getContenedor().getPeso();
    Double volumenContenedor= solicitud.getContenedor().getVolumen();
    Double pesoMaxCamion    = camion.getCapacidadPeso();
    Double volMaxCamion     = camion.getCapacidadVolumen();

    if (pesoContenedor > pesoMaxCamion) {
        throw new IllegalStateException(
            String.format("No se puede asignar: peso del contenedor (%.2f) excede capacidad del camión (%.2f)",
                          pesoContenedor, pesoMaxCamion));
    }
    if (volumenContenedor > volMaxCamion) {
        throw new IllegalStateException(
            String.format("No se puede asignar: volumen del contenedor (%.2f) excede capacidad del camión (%.2f)",
                          volumenContenedor, volMaxCamion));
    }

    solicitud.setCamion(camion);
    return map(repo.save(solicitud));
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
            .ciudadOrigenId(e.getCiudadOrigenId())
            .ciudadDestinoId(e.getCiudadDestinoId())
            .depositoId(e.getDeposito().getId())
            .camionId(e.getCamion() != null ? e.getCamion().getId() : null)
            .costoEstimado(e.getCostoEstimado())
            .tiempoEstimadoHoras(e.getTiempoEstimadoHoras())
            .build();
    }

}
