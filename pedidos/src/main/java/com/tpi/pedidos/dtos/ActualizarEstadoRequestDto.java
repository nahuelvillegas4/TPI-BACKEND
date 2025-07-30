package com.tpi.pedidos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la petición de actualización de estado al otro microservicio.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarEstadoRequestDto {
    private Long idSolicitud;
    private String estadoContenedor;
}