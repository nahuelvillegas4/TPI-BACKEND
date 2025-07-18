package tpi.backend.logistica.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import tpi.backend.logistica.entities.Deposito;
import tpi.backend.logistica.repository.DepositoRepository;

@Service
public class DepositoService {
    
    private final DepositoRepository depositoRepository;

    public DepositoService(DepositoRepository depositoRepository){
        this.depositoRepository = depositoRepository;
    }

    public Optional<Deposito> obtenerDeposito(long idDeposito){
        return depositoRepository.findById(idDeposito);
    }
}
