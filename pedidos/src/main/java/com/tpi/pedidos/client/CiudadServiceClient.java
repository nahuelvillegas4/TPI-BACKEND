package com.tpi.pedidos.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;



@Service
public class CiudadServiceClient {
    private final RestTemplate restTemplate = new RestTemplate();
    
    @Value("${ciudad.service.url}")
    private  String BASE_URL;

    public boolean ciudadExiste(Long ciudadId) {
        try {
            // GET a /logistica/ciudades/{id}
            restTemplate.getForObject(BASE_URL + ciudadId, Object.class);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
