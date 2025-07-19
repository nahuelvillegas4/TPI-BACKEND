package com.tpi.admin.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CiudadDto {

    private Long id;
    private String nombre;
    private Double latitud;
    private Double longitud;

}
