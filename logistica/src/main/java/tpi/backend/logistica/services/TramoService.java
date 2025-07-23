package tpi.backend.logistica.services;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import tpi.backend.logistica.dtos.Tramo_rutaDTO;
import tpi.backend.logistica.entities.Ciudad;
import tpi.backend.logistica.entities.TipoTramo;
import tpi.backend.logistica.entities.Tramo_Ruta;
import tpi.backend.logistica.repository.TramoRutaRepository;

@Service
public class TramoService {
    
    private final TramoRutaRepository tramoRutaRepository;

    public TramoService(TramoRutaRepository tramoRutaRepository){
        this.tramoRutaRepository = tramoRutaRepository;
    }

    public Tramo_rutaDTO crearTramo(Tramo_Ruta tramo){
        tramoRutaRepository.save(tramo);
        Tramo_rutaDTO respuesta = new Tramo_rutaDTO(tramo.getIdsolicitud(),tramo.getOrden());
        return respuesta;
    }


}
