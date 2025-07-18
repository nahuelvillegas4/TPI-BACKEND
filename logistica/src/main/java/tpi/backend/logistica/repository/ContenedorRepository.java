package tpi.backend.logistica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpi.backend.logistica.entities.Contenedor;

@Repository
public interface ContenedorRepository extends JpaRepository<Contenedor, Long>{
    
    Optional<Contenedor> findById(Long idContenedor);
}
