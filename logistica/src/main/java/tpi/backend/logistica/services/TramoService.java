package tpi.backend.logistica.services;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tpi.backend.logistica.dtos.ActualizarTramoDTO;
import tpi.backend.logistica.dtos.DatosRespuestaActualizacionDTO;
import tpi.backend.logistica.dtos.Tramo_rutaDTO;
import tpi.backend.logistica.entities.Ciudad;
import tpi.backend.logistica.entities.TarifaBase;
import tpi.backend.logistica.entities.TipoTramo;
import tpi.backend.logistica.entities.Tramo_Ruta;
import tpi.backend.logistica.repository.TarifaBaseRepository;
import tpi.backend.logistica.repository.TramoRutaRepository;

@Service
public class TramoService {
    
    private final TramoRutaRepository tramoRutaRepository;
    private final TarifaBaseService tarifaBaseService;

    public TramoService(TramoRutaRepository tramoRutaRepository, TarifaBaseService tarifaBaseService){
        this.tramoRutaRepository = tramoRutaRepository;
        this.tarifaBaseService = tarifaBaseService;
    }

    public Tramo_rutaDTO crearTramo(Tramo_Ruta tramo){
        tramoRutaRepository.save(tramo);
        Tramo_rutaDTO respuesta = new Tramo_rutaDTO(tramo.getIdsolicitud(),tramo.getOrden());
        return respuesta;
    }

    public DatosRespuestaActualizacionDTO actualizarTramo(ActualizarTramoDTO tramos){
        Tramo_Ruta tramo = null;
        Tramo_Ruta tramo1 = null;
        Double costoDias = (double) 0;
        System.out.println(tramos);

        if (tramos.estadoContenedor().equals("Retirado_de_origen")||tramos.estadoContenedor().equals("Entregado_en_depósito")) {
            System.out.println("entro");
            tramo = tramoRutaRepository.findByIdsolicitudAndOrden(tramos.idSolicitud(), 1);
        }
        if (tramos.estadoContenedor().equals("Retirado_de_depósito") || tramos.estadoContenedor().equals("Entregado_en_destino")) {
            tramo = tramoRutaRepository.findByIdsolicitudAndOrden(tramos.idSolicitud(), 2);
        }

        if (tramos.estadoContenedor().equals("Retirado_de_origen")) {
            tramo.setFechaRealSalida(new Timestamp(System.currentTimeMillis()));
            tramoRutaRepository.save(tramo);
        }

        if (tramos.estadoContenedor().equals("Entregado_en_depósito")) {
            tramo.setFechaRealLlegada(new Timestamp(System.currentTimeMillis()));
            tramoRutaRepository.save(tramo);
        }

        if (tramos.estadoContenedor().equals("Retirado_de_depósito")) {
            tramo1 = tramoRutaRepository.findByIdsolicitudAndOrden(tramos.idSolicitud(), 1);
            long unDiaEnMs = 24L * 60 * 60 * 1000;  // milisegundos en un día
            tramo.setFechaRealSalida(
                new Timestamp(System.currentTimeMillis() + unDiaEnMs)
            );
            costoDias = calcularCostoTarifaDeposito(tramo1, tramo);
            tramoRutaRepository.save(tramo);
        }


        if (tramos.estadoContenedor().equals("Entregado_en_destino")) {
            tramo.setFechaRealLlegada(new Timestamp(System.currentTimeMillis()));
            tramoRutaRepository.save(tramo);
        }

        DatosRespuestaActualizacionDTO respuesta = new DatosRespuestaActualizacionDTO(tramo.getIdsolicitud(), tramo.getOrden(), tramo.getFechaEstimadaSalida(),
                            tramo.getFechaEstimadaLlegada(), tramo.getFechaRealSalida(), tramo.getFechaRealLlegada(), costoDias);

        return respuesta;
    }

    private Double calcularCostoTarifaDeposito(Tramo_Ruta tramo1, Tramo_Ruta tramo2) {
        double diferencia = tramo2.getFechaRealSalida().getTime() - tramo1.getFechaRealLlegada().getTime();
        TarifaBase tarifaBase = tarifaBaseService
                            .obtenerTarifaBase(1)
                            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));

        double diferenciaDias = diferencia / (1000* 60 * 60 * 24);

        if (diferenciaDias < 1){
            return 0.0;
        }

        return diferenciaDias * tarifaBase.getTarifa();
    }

}
