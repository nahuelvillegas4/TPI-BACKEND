package tpi.backend.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tpi.backend.logistica.entities.TarifaBase;

public interface TarifaBaseRepository extends JpaRepository<TarifaBase, Long>{
    
}
