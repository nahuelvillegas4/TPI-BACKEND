package utnfc.isi.back.spring.geoapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import utnfc.isi.back.spring.geoapi.model.DistanciaDTO;

@Service
@RequiredArgsConstructor
public class GeoService {

    @Value("${google.maps.apikey}")
    private String apiKey;

    public DistanciaDTO calcularDistancia(String origen, String destino) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json" +
                     "?origins="      + origen +
                     "&destinations=" + destino +
                     "&units=metric"  +
                     "&key="          + apiKey;

        String body;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            body = response.getBody();
        } catch (RestClientException ex) {
            throw new RuntimeException("Error al llamar a Google Maps API", ex);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode element = mapper
                .readTree(body)
                .path("rows").get(0)
                .path("elements").get(0);

            double distanciaMeters = element
                .path("distance").path("value")
                .asDouble();
            double distanciaKm = distanciaMeters / 1000.0;

            String duracionText = element
                .path("duration").path("text")
                .asText(); // e.g. "1 hour 5 mins" o "45 mins"

            // Parseo robusto de la duración
            String horasParte   = "0 horas";
            String minutosParte = "0 minutos";
            String[] parts = duracionText.split(" ");
            if (parts.length == 4) {
                // ["1","hour","5","mins"]
                horasParte   = parts[0] + " " + parts[1];
                minutosParte = parts[2] + " " + parts[3];
            } else if (parts.length == 2) {
                // ["45","mins"]
                minutosParte = parts[0] + " " + parts[1];
            } else {
                // Caso inesperado: dejar el texto completo en minutos
                minutosParte = duracionText;
            }

            DistanciaDTO dto = new DistanciaDTO();
            dto.setOrigen(origen);
            dto.setDestino(destino);
            dto.setKilometros(distanciaKm);
            dto.setDuracionHoras(horasParte);
            dto.setDuracionMinutos(minutosParte);
            return dto;

        } catch (Exception ex) {
            throw new RuntimeException("Error al procesar la respuesta de Google Maps", ex);
        }
    }
}
