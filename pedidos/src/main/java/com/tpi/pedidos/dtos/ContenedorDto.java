// src/main/java/com/tpi/pedidos/dto/ContenedorDto.java
package com.tpi.pedidos.dtos;

import lombok.*;
import com.tpi.pedidos.entities.Estado;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContenedorDto {

    private Long id;
    private Double peso;
    private Double volumen;
    private Estado estado;
    private Long clienteId;
}
