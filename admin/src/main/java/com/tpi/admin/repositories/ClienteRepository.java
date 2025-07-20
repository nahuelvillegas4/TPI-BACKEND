// src/main/java/com/tpi/admin/repository/ClienteRepository.java
package com.tpi.admin.repositories;

import com.tpi.admin.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para Cliente.
 * Extiende JpaRepository, que ya provee:
 *   - Optional<Cliente> findById(Long id)
 *   - findAll, save, deleteById, etc.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // No necesitas declarar nada más para findById.
}
