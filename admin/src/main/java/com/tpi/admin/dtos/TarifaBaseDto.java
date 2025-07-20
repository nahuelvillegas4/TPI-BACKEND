package com.tpi.admin.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarifaBaseDto {
    private Long id;
    private Double tarifa;
}
