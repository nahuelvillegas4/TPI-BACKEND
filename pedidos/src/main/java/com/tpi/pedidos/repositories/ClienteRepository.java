// src/main/java/com/tpi/pedidos/repository/ClienteRepository.java
package com.tpi.pedidos.repositories;

import com.tpi.pedidos.entities.Cliente;
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
