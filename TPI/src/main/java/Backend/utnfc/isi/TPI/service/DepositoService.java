package Backend.utnfc.isi.TPI.service;

import Backend.utnfc.isi.TPI.models.Deposito;
import Backend.utnfc.isi.TPI.repository.DepositoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository depositoRepository;

    public List<Deposito> obtenerTodos() {
        return depositoRepository.findAll();
    }

    public Optional<Deposito> obtenerPorId(Long id) {
        return depositoRepository.findById(id);
    }

    public Deposito guardar(Deposito deposito) {
        return depositoRepository.save(deposito);
    }

    public void eliminar(Long id) {
        depositoRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return depositoRepository.existsById(id);
    }
}