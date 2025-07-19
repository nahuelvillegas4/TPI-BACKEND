package com.tpi.admin.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarCiudadDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "La latitud es obligatoria")
    @DecimalMin(value = "-90.0", message = "Latitud mínima -90")
    @DecimalMax(value = "90.0",  message = "Latitud máxima 90")
    private Double latitud;

    @NotNull(message = "La longitud es obligatoria")
    @DecimalMin(value = "-180.0", message = "Longitud mínima -180")
    @DecimalMax(value = "180.0",  message = "Longitud máxima 180")
    private Double longitud;

}
