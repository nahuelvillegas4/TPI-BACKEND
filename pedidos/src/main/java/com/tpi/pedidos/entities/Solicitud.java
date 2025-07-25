package com.tpi.pedidos.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "solicitud")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "contenedor_id", nullable = false)
    private Contenedor contenedor;

    @Column(name = "ciudad_origen_id", nullable = false)
    private Long ciudadOrigenId; // Solo ID

    @Column(name = "ciudad_destino_id", nullable = false)
    private Long ciudadDestinoId; // Solo ID

    @ManyToOne @JoinColumn(name = "deposito_id", nullable = false)
    private Deposito deposito;

    /** Al principio puede ser null */
    @ManyToOne @JoinColumn(name = "camion_id", nullable = true)
    private Camion camion;

    @Column(name = "costo_estimado", nullable = true)
    private Double costoEstimado;

    @Column(name = "tiempo_estimado_horas", nullable = true)
    private Double tiempoEstimadoHoras;
}
