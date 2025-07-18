package tpi.backend.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tpi.backend.logistica.entities.TarifaKM;

public interface TarifaKMRepository extends JpaRepository<TarifaKM, Long> {
    
}
