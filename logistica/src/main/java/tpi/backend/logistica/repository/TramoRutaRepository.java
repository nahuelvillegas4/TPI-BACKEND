package tpi.backend.logistica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tpi.backend.logistica.dtos.Tramo_rutaDTO;
import tpi.backend.logistica.entities.Tramo_Ruta;

public interface TramoRutaRepository extends JpaRepository<Tramo_Ruta, Long> {

    Tramo_Ruta findByIdsolicitudAndOrden(long idSolicitud, int i);

    List<Tramo_Ruta> findByIdsolicitud(Long idSolicitud);
    
}
