package tpi.backend.logistica.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import tpi.backend.logistica.entities.TarifaBase;
import tpi.backend.logistica.repository.TarifaBaseRepository;

@Service
public class TarifaBaseService {

    private final TarifaBaseRepository tarifaBaseRepository;

    public TarifaBaseService(TarifaBaseRepository tarifaBaseRepository){
        this.tarifaBaseRepository = tarifaBaseRepository;
    }

    public Optional<TarifaBase> obtenerTarifaBase(long idTarifa){
        Optional<TarifaBase> tarifa = tarifaBaseRepository.findById(idTarifa);
        return tarifa;
    }
}
