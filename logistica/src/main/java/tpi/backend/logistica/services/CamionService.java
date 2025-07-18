package tpi.backend.logistica.services;

import java.util.List;

import org.springframework.stereotype.Service;

import tpi.backend.logistica.dtos.CamionDTO;
import tpi.backend.logistica.entities.Camion;
import tpi.backend.logistica.repository.CamionRepository;

@Service
public class CamionService {
    
    private final CamionRepository camionRepository;

    public CamionService(CamionRepository camionRepository){
        this.camionRepository = camionRepository;
    }

    public List<Camion> obtenerTodos(){
        return camionRepository.findAll();
    }

    public List<Camion> obtenerDisponibles(){
        return camionRepository.findByDisponibleTrue();
    }

    public CamionDTO transformarDTO(Camion camion){
        CamionDTO camionDTO = new CamionDTO(camion);
        return camionDTO;
    }
}
