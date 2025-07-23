// src/main/java/tpi/backend/logistica/clients/DepositoClient.java
package tpi.backend.logistica.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tpi.backend.logistica.dtos.DepositoDTO;

@FeignClient(name = "pedidos", url = "${deposito.service.url}")
public interface DepositoClient {
    @GetMapping("/pedidos/depositos/{id}")
    DepositoDTO getDepositoById(@PathVariable("id") Long id);
}
