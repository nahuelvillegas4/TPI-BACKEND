package com.tpi.pedidos.dtos;


import lombok.*;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ClienteDto {
    
    private Long id;
    private String nombre;
    private String email;

}
