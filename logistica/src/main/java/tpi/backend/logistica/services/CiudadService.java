package tpi.backend.logistica.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import tpi.backend.logistica.entities.Ciudad;
import tpi.backend.logistica.repository.CiudadRepository;

@Service
public class CiudadService {
    
    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository){
        this.ciudadRepository = ciudadRepository;
    }

    public Optional<Ciudad> obtenerCiudad(Long idCiudad){
        return ciudadRepository.findById(idCiudad);
    }

}
