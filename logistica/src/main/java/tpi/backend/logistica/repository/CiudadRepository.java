package tpi.backend.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tpi.backend.logistica.entities.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Long>{
    
}
