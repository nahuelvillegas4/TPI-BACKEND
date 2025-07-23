// src/main/java/tpi/backend/logistica/dtos/DepositoDTO.java
package tpi.backend.logistica.dtos;

import tpi.backend.logistica.entities.Ciudad;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DepositoDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("ciudadId")
    private Long idCiudad;

    @JsonProperty("latitud")
    private double latitud;

    @JsonProperty("longitud")
    private double longitud;
    // otros atributos del depósito
}
