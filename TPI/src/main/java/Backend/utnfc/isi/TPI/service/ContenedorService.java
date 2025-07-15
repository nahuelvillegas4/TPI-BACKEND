package Backend.utnfc.isi.TPI.service;

import Backend.utnfc.isi.TPI.models.Contenedor;
import Backend.utnfc.isi.TPI.repository.ContenedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContenedorService {

    @Autowired
    private ContenedorRepository contenedorRepository;

    public List<Contenedor> obtenerTodos() {
        return contenedorRepository.findAll();
    }

    public Optional<Contenedor> obtenerPorId(Long id) {
        return contenedorRepository.findById(id);
    }

    public Contenedor guardar(Contenedor contenedor) {
        return contenedorRepository.save(contenedor);
    }

    public void eliminar(Long id) {
        contenedorRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return contenedorRepository.existsById(id);
    }
}