package com.tpi.pedidos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Respuesta que recibimos del microservicio de planificación:
 * costo estimado y tiempo estimado en horas.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanificacionResponseDto {

    private TramoDto tramo1;
    private TramoDto tramo2;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TramoDto {
        private Long idSolicitud;
        private Integer orden;
    }
}

