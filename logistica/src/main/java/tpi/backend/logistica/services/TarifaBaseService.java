package tpi.backend.logistica.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tpi.backend.logistica.dtos.TarifaBaseDTO;
import tpi.backend.logistica.dtos.tarifaKMDTO;
import tpi.backend.logistica.dtos.ModTarifaBaseDTO;
import tpi.backend.logistica.dtos.ModTarifaKMDTO;
import tpi.backend.logistica.entities.TarifaBase;
import tpi.backend.logistica.entities.TarifaKM;
import tpi.backend.logistica.repository.TarifaBaseRepository;
import tpi.backend.logistica.repository.TarifaKMRepository;

@Service
public class TarifaBaseService {

    private final TarifaBaseRepository tarifaBaseRepository;
    private final TarifaKMRepository tarifaKMRepository;

    public TarifaBaseService(TarifaBaseRepository tarifaBaseRepository, TarifaKMRepository tarifaKMRepository){
        this.tarifaBaseRepository = tarifaBaseRepository;
        this.tarifaKMRepository = tarifaKMRepository;
    }

    public Optional<TarifaBase> obtenerTarifaBase(long idTarifa){
        Optional<TarifaBase> tarifa = tarifaBaseRepository.findById(idTarifa);
        return tarifa;
    }

    public TarifaBaseDTO modificarTarifa(Long id, ModTarifaBaseDTO modificacion) {
        
        TarifaBase tarifa = tarifaBaseRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));
        tarifa.setTarifa(modificacion.tarifa());
        tarifaBaseRepository.save(tarifa);

        TarifaBaseDTO respuesta = new TarifaBaseDTO(tarifa.getId(), tarifa.getTarifa());
        return respuesta;
    }

    public List<TarifaBaseDTO> obtenerTodasTarifasBase() {
        
        List<TarifaBase> tarifas = tarifaBaseRepository.findAll();
        List<TarifaBaseDTO> respuesta = tarifas.stream()
        .map(t -> new TarifaBaseDTO(t.getId(), t.getTarifa()))
        .collect(Collectors.toList());

        return respuesta;
    }

    public TarifaBaseDTO obtenerTarifaBase(Long id){
        TarifaBase tarifa = tarifaBaseRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));
        TarifaBaseDTO respuesta = new TarifaBaseDTO(tarifa.getId(), tarifa.getTarifa());
        return respuesta;
    }
}
