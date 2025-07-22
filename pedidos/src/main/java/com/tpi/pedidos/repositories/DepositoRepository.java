package com.tpi.pedidos.repositories;

import com.tpi.pedidos.entities.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepositoRepository extends JpaRepository<Deposito, Long> {

    /**
     * Opcional: listar depósitos de una ciudad determinada.
     */
    List<Deposito> findByCiudadId(Long ciudadId);

}
