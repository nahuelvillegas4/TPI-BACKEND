package com.tpi.admin.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deposito")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String direccion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ciudad_id", nullable = false)
    private Ciudad ciudad;

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

}
