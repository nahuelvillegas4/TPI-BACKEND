package tpi.backend.logistica.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import tpi.backend.logistica.entities.Tramo_Ruta;

@Data
public class DatosRespuestaPosteo {

    public Tramo_rutaDTO tramo1;
    public Tramo_rutaDTO tramo2;
    public double totalHorasEstimado;
    public double montoEstimado;

    public DatosRespuestaPosteo(Tramo_rutaDTO respuesta1, Tramo_rutaDTO respuesta2, double horas, double monto) {
        this.tramo1 = respuesta1;
        this.tramo2 = respuesta2;
        this.totalHorasEstimado = horas;
        this.montoEstimado = monto;
    }
}
