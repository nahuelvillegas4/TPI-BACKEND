package com.tpi.pedidos.entities;

import com.tpi.pedidos.entities.Estado;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CambioEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long solicitudId;

    @Enumerated(EnumType.STRING)
    private Estado estadoAnterior;

    @Enumerated(EnumType.STRING)
    private Estado estadoNuevo;

    private LocalDateTime fechaCambio;
}
