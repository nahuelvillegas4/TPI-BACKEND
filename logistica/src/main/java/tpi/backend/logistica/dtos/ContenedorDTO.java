package tpi.backend.logistica.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ContenedorDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("peso")
    private double peso;

    @JsonProperty("volumen")
    private double volumen;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("clienteId")
    private Long idCliente;
    
}
            // .id(c.getId())
            // .peso(c.getPeso())
            // .volumen(c.getVolumen())
            // .estado(c.getEstado())
            // .clienteId(c.getCliente().getId())
            // .build();