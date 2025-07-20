// ActualizarCamionDto.java
package com.tpi.admin.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ActualizarCamionDto {

    @NotNull @Positive(message = "La capacidad de peso debe ser positiva")
    private Integer capacidadPeso;

    @NotNull @Positive(message = "La capacidad de volumen debe ser positiva")
    private Integer capacidadVolumen;

    @NotNull
    private Boolean disponible;
}
