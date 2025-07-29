package com.tpi.pedidos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * DTO que representa la respuesta del otro microservicio tras el PUT de estado.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosRespuestaActualizacionDto {
    private long idSolicitud;
    private int orden;
    private Timestamp fechaEstimadaSalida;
    private Timestamp fechaEstimadaLlegada;
    private Timestamp fechaRealSalida;
    private Timestamp fechaRealLlegada;
    private Double costoAdicionalEstadia;
}
