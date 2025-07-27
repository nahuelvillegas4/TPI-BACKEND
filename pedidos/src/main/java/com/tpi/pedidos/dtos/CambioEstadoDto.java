package com.tpi.pedidos.dtos;

import com.tpi.pedidos.entities.Estado;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CambioEstadoDto {
    private Estado estadoAnterior;
    private Estado estadoNuevo;
    private LocalDateTime fechaCambio;
}
