package tpi.backend.logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tpi.backend.logistica.entities.Tramo_Ruta;

public interface TramoRutaRepository extends JpaRepository<Tramo_Ruta, Long> {
    
}
