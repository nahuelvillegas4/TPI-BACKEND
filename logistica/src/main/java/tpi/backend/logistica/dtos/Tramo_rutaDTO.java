package tpi.backend.logistica.dtos;

import lombok.Data;

@Data
public class Tramo_rutaDTO {
    public long idSolicitud;
    public int orden;

    public Tramo_rutaDTO(long idSolicitud, int orden) {
        this.idSolicitud = idSolicitud;
        this.orden      = orden;
    }
}
