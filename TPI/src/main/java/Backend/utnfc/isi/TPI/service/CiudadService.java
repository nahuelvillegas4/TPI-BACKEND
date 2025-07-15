package Backend.utnfc.isi.TPI.service;

import Backend.utnfc.isi.TPI.models.Ciudad;
import Backend.utnfc.isi.TPI.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    public List<Ciudad> obtenerTodas() {
        return ciudadRepository.findAll();
    }

    public Optional<Ciudad> obtenerPorId(Long id) {
        return ciudadRepository.findById(id);
    }

    public Ciudad guardar(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    public void eliminar(Long id) {
        ciudadRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return ciudadRepository.existsById(id);
    }
}