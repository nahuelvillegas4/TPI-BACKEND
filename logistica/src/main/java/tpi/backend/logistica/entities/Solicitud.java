package tpi.backend.logistica.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contenedor_id")
    private Contenedor contenedor;

    @ManyToOne
    @JoinColumn(name = "ciudad_origen_id")
    private Ciudad ciudadOrigen;

    @ManyToOne
    @JoinColumn(name = "ciudad_destino_id")
    private Ciudad ciudadDestino;

    @ManyToOne
    @JoinColumn(name = "deposito_id")
    private Deposito deposito;

    @ManyToOne
    @JoinColumn(name = "camion_id")
    private Camion camion;

    private double costoEstimado;

    private double tiempoEstimadoHoras;

    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL)
    private List<TramoRuta> tramos;
}
