package tpi.backend.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpi.backend.logistica.entities.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long>{
    
}
