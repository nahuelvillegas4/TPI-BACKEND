package com.tpi.admin.repository;

import com.tpi.admin.model.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CamionRepository extends JpaRepository<Camion, Long> {

    /** Opcional: listar solo camiones disponibles */
    List<Camion> findByDisponibleTrue();
}
