package com.tpi.pedidos.dtos;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CamionDto {

    private Long id;
    private Double capacidadPeso;
    private Double capacidadVolumen;
    private Boolean disponible;
}
