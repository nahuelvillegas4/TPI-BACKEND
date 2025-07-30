package tpi.backend.logistica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpi.backend.logistica.entities.TarifaKM;

@Repository
public interface TarifaKMRepository extends JpaRepository<TarifaKM, Long> {

    TarifaKM findFirstByVolMaxGreaterThanEqualAndPesoMaxGreaterThanEqualOrderByVolMaxAscPesoMaxAsc(
            double volumenContenedor, double pesoContenedor);
    
    Optional<TarifaKM> findFirstByVolMaxAndPesoMax(double volMax, double pesoMax);
}
