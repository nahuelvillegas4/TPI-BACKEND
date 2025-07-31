package tpi.backend.logistica.dtos;

import java.sql.Timestamp;

import tpi.backend.logistica.entities.Ciudad;
import tpi.backend.logistica.entities.TipoTramo;

//import lombok.Data;

// @Data
// public class Tramo_rutaDTO {
//     public long idSolicitud;
//     public int orden;

//     public Tramo_rutaDTO(long idSolicitud, int orden) {
//         this.idSolicitud = idSolicitud;
//         this.orden      = orden;
//     }
// }

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
