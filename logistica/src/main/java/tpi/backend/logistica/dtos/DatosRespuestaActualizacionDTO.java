package tpi.backend.logistica.dtos;

import java.sql.Timestamp;

public record DatosRespuestaActualizacionDTO(
    long idSolicitud,
    int orden,
    Timestamp fechaEstimadaSalida,
    Timestamp fechaEstimadaLlegada,
    Timestamp fechaRealSalida,
    Timestamp fechaRealLlegada,
    Double costoAdicionalEstadia
) {
    
}
