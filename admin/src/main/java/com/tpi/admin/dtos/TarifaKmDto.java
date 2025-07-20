package com.tpi.admin.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarifaKmDto {
    private Long id;
    private Double tarifa;
}
