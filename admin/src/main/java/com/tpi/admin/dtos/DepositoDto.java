package com.tpi.admin.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepositoDto {

    private Long id;
    private String direccion;
    private Long ciudadId;
    private Double latitud;
    private Double longitud;
}
