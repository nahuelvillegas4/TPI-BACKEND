package Backend.utnfc.isi.TPI.service;

import Backend.utnfc.isi.TPI.models.Cliente;
import Backend.utnfc.isi.TPI.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

    public boolean existePorEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }

    public boolean existePorId(Long id) {
        return clienteRepository.existsById(id);
    }
}