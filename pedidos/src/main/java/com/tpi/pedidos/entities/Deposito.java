package com.tpi.pedidos.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deposito")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String direccion;

    // Solo el id de la ciudad (sin ManyToOne)
    @Column(name = "ciudad_id", nullable = false)
    private Long ciudadId;

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;
}
