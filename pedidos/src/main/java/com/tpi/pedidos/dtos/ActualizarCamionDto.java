// ActualizarCamionDto.java
package com.tpi.pedidos.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ActualizarCamionDto {

    @NotNull @Positive(message = "La capacidad de peso debe ser positiva")
    private Double capacidadPeso;

    @NotNull @Positive(message = "La capacidad de volumen debe ser positiva")
    private Double capacidadVolumen;

    @NotNull
    private Boolean disponible;
}
