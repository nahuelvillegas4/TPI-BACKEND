// src/main/java/com/tpi/pedidos/repository/ClienteRepository.java
package com.tpi.pedidos.repositories;

import com.tpi.pedidos.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
