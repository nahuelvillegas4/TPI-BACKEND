package Backend.utnfc.isi.TPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    private Double peso;

    @NotNull
    @Min(1)
    private Double volumen;

    @NotBlank
    private String estado; // Puede usarse CHECK o Enum si querés validación

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
} 
