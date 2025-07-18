package utnfc.isi.back.spring.geoapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import utnfc.isi.back.spring.geoapi.model.DistanciaDTO;
import utnfc.isi.back.spring.geoapi.service.GeoService;

@RestController
@RequestMapping("/api/distancia")
@RequiredArgsConstructor
public class GeoController {

    private final GeoService geoService;

    @GetMapping
    public DistanciaDTO obtenerDistancia(
            @RequestParam String origen,
            @RequestParam String destino) throws Exception {
    return geoService.calcularDistancia(origen, destino);
    }
}
