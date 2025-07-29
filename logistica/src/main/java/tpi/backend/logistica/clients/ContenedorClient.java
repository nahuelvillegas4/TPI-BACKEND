package tpi.backend.logistica.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tpi.backend.logistica.dtos.ContenedorDTO;

@FeignClient(name = "pedidos2", url = "${deposito.service.url}")
public interface ContenedorClient {
    @GetMapping("/pedidos/contenedores/{id}")
    ContenedorDTO getContenedorById(@PathVariable("id") Long id);
}
