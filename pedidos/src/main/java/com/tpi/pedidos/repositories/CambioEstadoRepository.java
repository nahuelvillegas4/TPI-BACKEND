package com.tpi.pedidos.repositories;

import com.tpi.pedidos.entities.CambioEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CambioEstadoRepository extends JpaRepository<CambioEstado, Long> {
    List<CambioEstado> findBySolicitudIdOrderByFechaCambioAsc(Long solicitudId);

}
