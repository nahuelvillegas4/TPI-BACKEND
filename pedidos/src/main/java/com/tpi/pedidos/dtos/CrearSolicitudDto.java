package com.tpi.pedidos.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CrearSolicitudDto {
    @NotNull private Long contenedorId;
    @NotNull private Long ciudadOrigenId;
    @NotNull private Long ciudadDestinoId;
    @NotNull private Long depositoId;
    /** Opcional al crear */
    private Long camionId;
    @NotNull private Double costoEstimado;
    @NotNull private Double tiempoEstimadoHoras;
}
