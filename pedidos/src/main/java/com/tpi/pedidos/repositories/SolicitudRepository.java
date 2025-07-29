package com.tpi.pedidos.repositories;

import com.tpi.pedidos.entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    boolean existsByCamionIdAndIdNot(Long camionId, Long solicitudId);
}
