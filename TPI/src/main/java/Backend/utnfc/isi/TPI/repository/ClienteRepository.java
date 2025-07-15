package Backend.utnfc.isi.TPI.repository;

import Backend.utnfc.isi.TPI.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);
}