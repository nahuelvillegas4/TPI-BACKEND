package com.tpi.admin.repository;

import com.tpi.admin.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    /**
     * Busca una ciudad por nombre (por si quieres validar unicidad antes de persistir).
     */
    Optional<Ciudad> findByNombre(String nombre);

}
