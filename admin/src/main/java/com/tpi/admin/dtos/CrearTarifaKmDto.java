package com.tpi.admin.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearTarifaKmDto {

    @NotNull(message = "La tarifa por kilómetro es obligatoria")
    @Positive(message = "La tarifa por kilómetro debe ser positiva")
    private Double tarifa;
}
