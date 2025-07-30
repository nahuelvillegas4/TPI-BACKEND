package tpi.backend.logistica.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tpi.backend.logistica.dtos.CrearTarifaDTO;
import tpi.backend.logistica.dtos.ModTarifaKMDTO;
import tpi.backend.logistica.dtos.tarifaKMDTO;
import tpi.backend.logistica.entities.TarifaKM;
import tpi.backend.logistica.repository.TarifaKMRepository;

@Service
public class TarifaKMService {

    private final TarifaKMRepository tarifaKMRepository;

    public TarifaKMService(TarifaKMRepository tarifaKMRepository){
        this.tarifaKMRepository = tarifaKMRepository;
    }

    public Double obtenerTarifa(double pesoContenedor, double volumenContenedor){

        TarifaKM tarifa = tarifaKMRepository
            .findFirstByVolMaxGreaterThanEqualAndPesoMaxGreaterThanEqualOrderByVolMaxAscPesoMaxAsc(volumenContenedor, pesoContenedor);
        return tarifa.getTarifa();
    }

    public tarifaKMDTO crearTarifaNueva(CrearTarifaDTO nuevaTarifaDTO) {
        Optional<TarifaKM> tarifa = tarifaKMRepository.findFirstByVolMaxAndPesoMax(nuevaTarifaDTO.volMax(), nuevaTarifaDTO.pesoMax());

        if (tarifa.isPresent()){
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Tarifa ya existente");
        }

        TarifaKM tarifaNueva = new TarifaKM(null, nuevaTarifaDTO.volMax(), nuevaTarifaDTO.pesoMax(), nuevaTarifaDTO.tarifa());
        tarifaKMRepository.save(tarifaNueva);

        tarifaKMDTO respuesta = new tarifaKMDTO(tarifaNueva.getId(), tarifaNueva.getVolMax(), tarifaNueva.getPesoMax(), tarifaNueva.getTarifa());

        return respuesta;
    }

    public tarifaKMDTO modificarTarifaKM(Long id, ModTarifaKMDTO modificacion) {
        
        TarifaKM tarifa = tarifaKMRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));

        if(modificacion.volMax() != null){
            tarifa.setVolMax(modificacion.volMax());
        }
        if(modificacion.pesoMax() != null){
            tarifa.setPesoMax(modificacion.pesoMax());
        }
        if(modificacion.tarifa() != null){
            tarifa.setTarifa(modificacion.tarifa());
        }
        tarifaKMRepository.save(tarifa);

        tarifaKMDTO respuesta = new tarifaKMDTO(tarifa.getId(), tarifa.getVolMax(),tarifa.getPesoMax(),tarifa.getTarifa());
        return respuesta;
    }

    public tarifaKMDTO obtenerTarifaKM(Long id){
        TarifaKM tarifa = tarifaKMRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));
        tarifaKMDTO respuesta = new tarifaKMDTO(tarifa.getId(), tarifa.getVolMax(),tarifa.getPesoMax(),tarifa.getTarifa());
        return respuesta;
    }
    
    public List<tarifaKMDTO> obtenerTarifasKMDTO() {
        
        List<TarifaKM> tarifas = tarifaKMRepository.findAll();
        List<tarifaKMDTO> respuesta = tarifas.stream()
        .map(t -> new tarifaKMDTO(t.getId(), t.getVolMax(), t.getPesoMax(),t.getTarifa()))
        .collect(Collectors.toList());

        return respuesta;
    }

    public void eliminarTarifaKM(Long id) {
    if (!tarifaKMRepository.existsById(id)) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada");
    }
    tarifaKMRepository.deleteById(id);
    }

}
