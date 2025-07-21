package tpi.backend.logistica.dtos;

import lombok.Data;

@Data
public class PostSolicitudDTO {

    private long idSolicitud;
    private long idCiudadOrigen;
    private long idCiudadDestino;
    private long idCiudadDeposito;

}
