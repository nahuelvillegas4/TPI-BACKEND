package tpi.backend.logistica.dtos;

public record CotizacionSolicitudDTO(
    long idDeposito,
    long idCiudadOrigen,
    long idCiudadDestino,
    long idContenedor
) {

}
