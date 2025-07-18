package tpi.backend.logistica.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import tpi.backend.logistica.dtos.ContenedorDTO;
import tpi.backend.logistica.entities.Contenedor;
import tpi.backend.logistica.repository.ContenedorRepository;

@Service
public class ContenedorService {

    private final ContenedorRepository contenedorRepository;

    public ContenedorService(ContenedorRepository contenedorRepository){
        this.contenedorRepository = contenedorRepository;
    }

    public Optional<Contenedor> obtenerContenedor(long idContenedor){
        Optional<Contenedor> contenedor = contenedorRepository.findById(idContenedor);
        return contenedor;
    }

    public ContenedorDTO transformarDTO(Contenedor contenedor){
        ContenedorDTO contenedorDTO = new ContenedorDTO(contenedor);
        return contenedorDTO;
    }
    
}
