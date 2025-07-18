package tpi.backend.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tpi.backend.logistica.entities.Deposito;

public interface DepositoRepository extends JpaRepository<Deposito, Long>{
    
}
