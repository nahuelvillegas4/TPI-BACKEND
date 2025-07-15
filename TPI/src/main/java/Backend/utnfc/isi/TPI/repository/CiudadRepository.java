package Backend.utnfc.isi.TPI.repository;

import Backend.utnfc.isi.TPI.models.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
