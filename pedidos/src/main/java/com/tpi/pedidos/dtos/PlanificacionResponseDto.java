package com.tpi.pedidos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanificacionResponseDto {

    private TramoDto tramo1;
    private TramoDto tramo2;

    /** Nuevo: total de horas estimado */
    @JsonProperty("totalHorasEstimado")
    private Double totalHorasEstimado;

    /** Nuevo: monto (costo) estimado */
    @JsonProperty("montoEstimado")
    private Double montoEstimado;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TramoDto {
        private Long idSolicitud;
        private Integer orden;
    }
}

