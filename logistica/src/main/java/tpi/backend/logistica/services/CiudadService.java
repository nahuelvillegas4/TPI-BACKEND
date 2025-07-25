package tpi.backend.logistica.services;

import java.util.Optional;
import java.util.List;

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

    public List<Ciudad> obtenerTodas(){
        return ciudadRepository.findAll();
    }

    public Ciudad registrarCiudad(Ciudad ciudad){
        return ciudadRepository.save(ciudad);
    }

    public Ciudad actualizarCiudad(Long id, Ciudad ciudadActualizada){
        return ciudadRepository.findById(id)
                .map(c -> {
                    c.setNombre(ciudadActualizada.getNombre());
                    c.setLatitud(ciudadActualizada.getLatitud());
                    c.setLongitud(ciudadActualizada.getLongitud());
                    return ciudadRepository.save(c);
                })
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
    }

    public void eliminarCiudad(Long id){
        Ciudad ciudad = ciudadRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
        ciudadRepository.delete(ciudad);
    }
}
