package tpi.backend.logistica.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import tpi.backend.logistica.entities.TarifaKM;
import tpi.backend.logistica.repository.TarifaKMRepository;

@Service
public class TarifaKMService {

    private final TarifaKMRepository tarifaKMRepository;

    public TarifaKMService(TarifaKMRepository tarifaKMRepository){
        this.tarifaKMRepository = tarifaKMRepository;
    }

    public Optional<TarifaKM> obtenerTarifa(long idTarifa){
        Optional<TarifaKM> tarifa = tarifaKMRepository.findById(idTarifa);
        return tarifa;
    }
    
}
