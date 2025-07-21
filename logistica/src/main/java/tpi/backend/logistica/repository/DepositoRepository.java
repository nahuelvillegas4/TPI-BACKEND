package tpi.backend.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpi.backend.logistica.entities.Deposito;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long>{
    
}
