package com.tpi.pedidos.repositories;

import com.tpi.pedidos.entities.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CamionRepository extends JpaRepository<Camion, Long> {

    List<Camion> findByDisponibleTrue();
}
