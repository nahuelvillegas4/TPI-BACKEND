// src/main/java/com/tpi/admin/dto/ActualizarContenedorDto.java
package com.tpi.admin.dtos;

import com.tpi.admin.entities.Estado;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActualizarContenedorDto {

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser un valor positivo")
    private Double peso;

    @NotNull(message = "El volumen es obligatorio")
    @Positive(message = "El volumen debe ser un valor positivo")
    private Double volumen;

    @NotNull(message = "El estado es obligatorio")
    private Estado estado;

    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;
}
