package com.tpi.pedidos.dtos;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SolicitudDto {
    private Long id;
    private Long contenedorId;
    private Long ciudadOrigenId;
    private Long ciudadDestinoId;
    private Long depositoId;
    private Long camionId;
    private Double costoEstimado;
    private Double tiempoEstimadoHoras;
}
