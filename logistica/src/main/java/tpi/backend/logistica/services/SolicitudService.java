package tpi.backend.logistica.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import tpi.backend.logistica.entities.Solicitud;
import tpi.backend.logistica.repository.SolicitudRepository;

@Service
public class SolicitudService {
    
    private final SolicitudRepository solicitudRepository;

    public SolicitudService(SolicitudRepository solicitudRepository){
        this.solicitudRepository = solicitudRepository;
    }

    public Optional<Solicitud> obtenerSolicitud(long idSolicitud){
        return solicitudRepository.findById(idSolicitud);
    }

}
