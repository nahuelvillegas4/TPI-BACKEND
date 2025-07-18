package tpi.backend.logistica.dtos;

import tpi.backend.logistica.entities.Contenedor;
import tpi.backend.logistica.entities.Estado;

public record ContenedorDTO(
    long id,
    double peso,
    double volumen,
    Estado estado
) {
    public ContenedorDTO(Contenedor contenedor){
        this(contenedor.getId(), contenedor.getPeso(), contenedor.getVolumen(), contenedor.getEstado());
    }
}
