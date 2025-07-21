package tpi.backend.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpi.backend.logistica.entities.TarifaBase;

@Repository
public interface TarifaBaseRepository extends JpaRepository<TarifaBase, Long>{
    
}
