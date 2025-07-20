package com.tpi.admin.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearTarifaBaseDto {

    @NotNull(message = "La tarifa base es obligatoria")
    @Positive(message = "La tarifa base debe ser positiva")
    private Double tarifa;
}
