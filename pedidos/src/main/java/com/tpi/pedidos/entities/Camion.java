package com.tpi.pedidos.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CAMION")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Capacidad máxima de peso en kg */
    @Column(name = "capacidad_peso", nullable = false)
    private Double capacidadPeso;

    /** Capacidad máxima de volumen en m³ */
    @Column(name = "capacidad_volumen", nullable = false)
    private Double capacidadVolumen;

    /** Indica si está disponible para nuevas rutas */
    @Column(nullable = false)
    private Boolean disponible;
}
