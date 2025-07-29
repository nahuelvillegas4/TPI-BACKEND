package tpi.backend.logistica.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostSolicitudDTO {

    private Long idSolicitud;
    private Long idCiudadOrigen;
    private Long idCiudadDestino;
    private Long idDeposito;
    private Long idContenedor;

    // Constructor vacío (para Jackson)
    public PostSolicitudDTO() { }

    // Constructor completo
    public PostSolicitudDTO(
        @JsonProperty("idSolicitud")      Long idSolicitud,
        @JsonProperty("idCiudadOrigen")   Long idCiudadOrigen,
        @JsonProperty("idCiudadDestino")  Long idCiudadDestino,
        @JsonProperty("idDeposito") Long idDeposito,
        @JsonProperty("idContenedor") Long idContenedor
    ) {
        this.idSolicitud       = idSolicitud;
        this.idCiudadOrigen    = idCiudadOrigen;
        this.idCiudadDestino   = idCiudadDestino;
        this.idDeposito  = idDeposito;
        this.idContenedor = idContenedor;
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

    public Long getidDeposito() {
        return idDeposito;
    }
    public void setidDeposito(Long idDeposito) {
        this.idDeposito = idDeposito;
    }

    public Long getidContenedor(){
        return idContenedor;
    }

    public void setidContenedor(Long idContenedor){
        this.idContenedor = idContenedor;
    }

    @Override
    public String toString() {
        return "PostSolicitudDTO{" +
            "idSolicitud="       + idSolicitud +
            ", idCiudadOrigen="  + idCiudadOrigen +
            ", idCiudadDestino=" + idCiudadDestino +
            ", idDeposito="+ idDeposito +
            '}';
    }
}
