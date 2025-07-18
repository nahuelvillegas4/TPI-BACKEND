package tpi.backend.logistica.dtos;

import tpi.backend.logistica.entities.Camion;

public record CamionDTO(
    double capacidad,
    double volumen,
    boolean estado,
    long id
) {
    public CamionDTO(Camion camion){
        this(camion.getCapacidadPeso(), camion.getCapacidadVolumen(), camion.getDisponible() ,camion.getId());
    }
}
