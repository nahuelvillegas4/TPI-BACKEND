package com.tpi.pedidos.dtos;

import jakarta.validation.constraints.*;
import com.tpi.pedidos.entities.Estado;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearContenedorDto {

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser un valor positivo")
    private Double peso;

    @NotNull(message = "El volumen es obligatorio")
    @Positive(message = "El volumen debe ser un valor positivo")
    private Double volumen;

    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;
}
