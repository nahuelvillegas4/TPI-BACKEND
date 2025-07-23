package tpi.backend.logistica.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostSolicitudDTO {

    private Long idSolicitud;
    private Long idCiudadOrigen;
    private Long idCiudadDestino;
    private Long idCiudadDeposito;

    // Constructor vacío (para Jackson)
    public PostSolicitudDTO() { }

    // Constructor completo
    public PostSolicitudDTO(
        @JsonProperty("idSolicitud")      Long idSolicitud,
        @JsonProperty("idCiudadOrigen")   Long idCiudadOrigen,
        @JsonProperty("idCiudadDestino")  Long idCiudadDestino,
        @JsonProperty("idCiudadDeposito") Long idCiudadDeposito
    ) {
        this.idSolicitud       = idSolicitud;
        this.idCiudadOrigen    = idCiudadOrigen;
        this.idCiudadDestino   = idCiudadDestino;
        this.idCiudadDeposito  = idCiudadDeposito;
    }

    // Getters y setters
    public Long getIdSolicitud() {
        return idSolicitud;
    }
    public void setIdSolicitud(Long idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Long getIdCiudadOrigen() {
        return idCiudadOrigen;
    }
    public void setIdCiudadOrigen(Long idCiudadOrigen) {
        this.idCiudadOrigen = idCiudadOrigen;
    }

    public Long getIdCiudadDestino() {
        return idCiudadDestino;
    }
    public void setIdCiudadDestino(Long idCiudadDestino) {
        this.idCiudadDestino = idCiudadDestino;
    }

    public Long getIdCiudadDeposito() {
        return idCiudadDeposito;
    }
    public void setIdCiudadDeposito(Long idCiudadDeposito) {
        this.idCiudadDeposito = idCiudadDeposito;
    }

    @Override
    public String toString() {
        return "PostSolicitudDTO{" +
            "idSolicitud="       + idSolicitud +
            ", idCiudadOrigen="  + idCiudadOrigen +
            ", idCiudadDestino=" + idCiudadDestino +
            ", idCiudadDeposito="+ idCiudadDeposito +
            '}';
    }
}
