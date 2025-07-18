package tpi.backend.logistica.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "La capacidad de peso debe ser mayor a cero")
    @Column(nullable = false)
    private Double capacidadPeso;

    @Min(value = 1, message = "La capacidad de volumen debe ser mayor a cero")
    @Column(nullable = false)
    private Double capacidadVolumen;

    @Column(nullable = false)
    private Boolean disponible;
}