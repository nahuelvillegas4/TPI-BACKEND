// CamionDto.java
package com.tpi.pedidos.dtos;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CamionDto {

    private Long id;
    private Integer capacidadPeso;
    private Integer capacidadVolumen;
    private Boolean disponible;
}
