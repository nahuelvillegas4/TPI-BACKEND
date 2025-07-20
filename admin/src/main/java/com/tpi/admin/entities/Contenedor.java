package com.tpi.admin.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contenedor")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Peso máximo que soporta (kg) */
    @Column(nullable = false)
    private Double peso;

    /** Volumen máximo que soporta (m³) */
    @Column(nullable = false)
    private Double volumen;

    @Enumerated(EnumType.STRING)
    private Estado estado; // Puede usarse CHECK o Enum si querés validación

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

}
