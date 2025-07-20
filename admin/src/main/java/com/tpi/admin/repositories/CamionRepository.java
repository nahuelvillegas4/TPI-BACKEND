package com.tpi.admin.repositories;

import com.tpi.admin.entities.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CamionRepository extends JpaRepository<Camion, Long> {

    /** Opcional: listar solo camiones disponibles */
    List<Camion> findByDisponibleTrue();
}
