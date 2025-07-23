package com.tpi.pedidos.entities;

import com.tpi.pedidos.entities.*;
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

    @ManyToOne @JoinColumn(name = "ciudad_origen_id", nullable = false)
    private Ciudad ciudadOrigen;

    @ManyToOne @JoinColumn(name = "ciudad_destino_id", nullable = false)
    private Ciudad ciudadDestino;

    @ManyToOne @JoinColumn(name = "deposito_id", nullable = false)
    private Deposito deposito;

    /** Al principio puede ser null */
    @ManyToOne @JoinColumn(name = "camion_id", nullable = true)
    private Camion camion;

    @Column(name = "costo_estimado", nullable = false)
    private Double costoEstimado;

    @Column(name = "tiempo_estimado_horas", nullable = false)
    private Double tiempoEstimadoHoras;
}
