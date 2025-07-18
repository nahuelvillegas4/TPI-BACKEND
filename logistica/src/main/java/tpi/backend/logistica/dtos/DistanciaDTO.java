package tpi.backend.logistica.dtos;

import lombok.Data;

@Data
public class DistanciaDTO {
    private String origen;
    private String destino;
    private double kilometros;
    private String duracionHoras;
    private String duracionMinutos;
}

