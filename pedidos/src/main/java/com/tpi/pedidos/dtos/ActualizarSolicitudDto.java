package com.tpi.pedidos.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ActualizarSolicitudDto {
    @NotNull private Double costoEstimado;
    @NotNull private Double tiempoEstimadoHoras;
    
}
