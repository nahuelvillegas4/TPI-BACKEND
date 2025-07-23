// src/main/java/com/tpi/pedidos/repository/ContenedorRepository.java
package com.tpi.pedidos.repositories;

import com.tpi.pedidos.entities.Contenedor;
import com.tpi.pedidos.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContenedorRepository extends JpaRepository<Contenedor, Long> {

    /**
     * Permite filtrar contenedores por su estado (por ejemplo DISPONIBLE, EN_USO, MANTENIMIENTO…).
     */
    List<Contenedor> findByEstado(Estado estado);

}
