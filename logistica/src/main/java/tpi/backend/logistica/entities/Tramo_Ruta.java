package tpi.backend.logistica.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tramo_Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "solicitud_id")
    private long idsolicitud;

    private int orden;

    @Enumerated(EnumType.STRING)
    private TipoTramo tipoTramo;

    @ManyToOne
    @JoinColumn(name = "ciudad_origen_id")
    private Ciudad ciudadOrigen;

    @ManyToOne
    @JoinColumn(name = "ciudad_destino_id")
    private Ciudad ciudadDestino;
    
    private Timestamp fechaEstimadaSalida;

    private Timestamp fechaEstimadaLlegada;

    private Timestamp fechaRealSalida;

    private Timestamp fechaRealLlegada;
}

