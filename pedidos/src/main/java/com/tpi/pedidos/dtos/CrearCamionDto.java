// CrearCamionDto.java
package com.tpi.pedidos.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CrearCamionDto {

    @NotNull @Positive(message = "La capacidad de peso debe ser positiva")
    private Integer capacidadPeso;

    @NotNull @Positive(message = "La capacidad de volumen debe ser positiva")
    private Integer capacidadVolumen;

    @NotNull
    private Boolean disponible;
}
