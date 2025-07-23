package tpi.backend.logistica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import tpi.backend.logistica.entities.Tramo_Ruta;

@Data
public class DatosRespuestaPosteo {

    public Tramo_rutaDTO tramo1;
    public Tramo_rutaDTO tramo2;

    public DatosRespuestaPosteo(Tramo_rutaDTO respuesta1, Tramo_rutaDTO respuesta2) {
        this.tramo1 = respuesta1;
        this.tramo2 = respuesta2;
    }
}
