package Backend.utnfc.isi.TPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Backend.utnfc.isi.TPI.models.Deposito;

public interface DepositoRepository extends JpaRepository<Deposito, Long>{

}
