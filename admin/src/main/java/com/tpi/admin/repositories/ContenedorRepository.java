// src/main/java/com/tpi/admin/repository/ContenedorRepository.java
package com.tpi.admin.repositories;

import com.tpi.admin.entities.Contenedor;
import com.tpi.admin.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContenedorRepository extends JpaRepository<Contenedor, Long> {

    /**
     * Permite filtrar contenedores por su estado (por ejemplo DISPONIBLE, EN_USO, MANTENIMIENTO…).
     */
    List<Contenedor> findByEstado(Estado estado);

}
