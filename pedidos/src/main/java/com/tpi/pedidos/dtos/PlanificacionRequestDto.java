package com.tpi.pedidos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanificacionRequestDto {
    private Long idSolicitud;
    private Long idCiudadOrigen;
    private Long idCiudadDestino;
    private Long idDeposito;
    private Long idContenedor;
}
