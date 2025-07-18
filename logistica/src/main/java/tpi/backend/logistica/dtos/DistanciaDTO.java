package tpi.backend.logistica.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DistanciaDTO {
    private String origen;
    private String destino;
    private double kilometros;
    private String duracionTexto;
    
}

