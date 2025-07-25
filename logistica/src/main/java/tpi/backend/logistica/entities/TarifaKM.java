package tpi.backend.logistica.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tarifa_km")
public class TarifaKM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double volMax;

    private double pesoMax;

    private Double tarifa;
    
}
