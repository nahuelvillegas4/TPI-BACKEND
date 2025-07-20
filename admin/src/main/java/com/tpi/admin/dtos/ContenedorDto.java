// src/main/java/com/tpi/admin/dto/ContenedorDto.java
package com.tpi.admin.dtos;

import lombok.*;
import com.tpi.admin.entities.Estado;

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
