package tpi.backend.logistica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpi.backend.logistica.entities.Camion;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long>{

    Optional<Camion> findById(Long idCamion);

    List<Camion> findAll();

    List<Camion> findByDisponibleTrue();
    
}
