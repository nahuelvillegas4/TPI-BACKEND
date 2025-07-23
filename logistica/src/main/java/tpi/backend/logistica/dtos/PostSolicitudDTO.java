package tpi.backend.logistica.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostSolicitudDTO {

    private Long idSolicitud;
    private Long idCiudadOrigen;
    private Long idCiudadDestino;
    private Long idCiudadDeposito;
    private Double latitudDeposito;
    private Double longitudDeposito;

    // Constructor vacío (necesario para Jackson)
    public PostSolicitudDTO() { }

    // Constructor con todos los campos (opcional, para tu conveniencia)
    public PostSolicitudDTO(
        @JsonProperty("idSolicitud") Long idSolicitud,
        @JsonProperty("idCiudadOrigen") Long idCiudadOrigen,
        @JsonProperty("idCiudadDestino") Long idCiudadDestino,
        @JsonProperty("idCiudadDeposito") Long idCiudadDeposito,
        @JsonProperty("latitudDeposito") Double latitudDeposito,
        @JsonProperty("longitudDeposito") Double longitudDeposito
    ) {
        this.idSolicitud = idSolicitud;
        this.idCiudadOrigen = idCiudadOrigen;
        this.idCiudadDestino = idCiudadDestino;
        this.idCiudadDeposito = idCiudadDeposito;
        this.latitudDeposito = latitudDeposito;
        this.longitudDeposito = longitudDeposito;
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

    public Double getLatitudDeposito() {
        return latitudDeposito;
    }

    public void setLatitudDeposito(Double latitudDeposito) {
        this.latitudDeposito = latitudDeposito;
    }

    public Double getLongitudDeposito() {
        return longitudDeposito;
    }

    public void setLongitudDeposito(Double longitudDeposito) {
        this.longitudDeposito = longitudDeposito;
    }

    @Override
    public String toString() {
        return "PostSolicitudDTO{" +
            "idSolicitud=" + idSolicitud +
            ", idCiudadOrigen=" + idCiudadOrigen +
            ", idCiudadDestino=" + idCiudadDestino +
            ", idCiudadDeposito=" + idCiudadDeposito +
            ", latitudDeposito=" + latitudDeposito +
            ", longitudDeposito=" + longitudDeposito +
            '}';
    }
}
