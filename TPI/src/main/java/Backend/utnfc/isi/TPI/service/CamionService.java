package Backend.utnfc.isi.TPI.service;

import Backend.utnfc.isi.TPI.models.Camion;
import Backend.utnfc.isi.TPI.repository.CamionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CamionService {

    @Autowired
    private CamionRepository camionRepository;

    public List<Camion> obtenerTodos() {
        return camionRepository.findAll();
    }

    public Optional<Camion> obtenerPorId(Long id) {
        return camionRepository.findById(id);
    }

    public Camion guardar(Camion camion) {
        return camionRepository.save(camion);
    }

    public void eliminar(Long id) {
        camionRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return camionRepository.existsById(id);
    }
}
