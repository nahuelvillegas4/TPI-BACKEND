package tpi.backend.logistica.dtos;

import java.sql.Timestamp;

import tpi.backend.logistica.entities.Ciudad;
import tpi.backend.logistica.entities.TipoTramo;


public record Tramo_rutaDTO(
    Long idSolicitud,
    int orden,
    TipoTramo tipoTramo,
    Long idCiudadOrigen,
    Long idCiudadDestino,
    Timestamp fechaEstimadaSalida,
    Timestamp fechaEstimadaLlegada,
    Timestamp fechaRealSalida,
    Timestamp fechaRealLlegada
) {
}
