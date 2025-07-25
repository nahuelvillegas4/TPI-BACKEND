package com.tpi.pedidos.services;

import com.tpi.pedidos.dtos.ContenedorDto;
import com.tpi.pedidos.dtos.CrearContenedorDto;
import com.tpi.pedidos.dtos.ActualizarContenedorDto;
import com.tpi.pedidos.exception.EntityNotFoundException;
import com.tpi.pedidos.entities.Contenedor;
import com.tpi.pedidos.entities.Cliente;
import com.tpi.pedidos.entities.Estado;
import com.tpi.pedidos.repositories.ContenedorRepository;
import com.tpi.pedidos.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContenedorService {

    private final ContenedorRepository contenedorRepo;
    private final ClienteRepository clienteRepo;

    @Transactional
    public ContenedorDto crear(CrearContenedorDto dto) {
        Cliente cliente = clienteRepo.findById(dto.getClienteId())
            .orElseThrow(() -> new EntityNotFoundException(
                "Cliente no encontrado con id " + dto.getClienteId()));

        // Usar el estado que viene, o el por defecto si es null
        Estado estado = (dto.getEstado() != null) ? dto.getEstado() : Estado.en_espera_solicitud;

        Contenedor entidad = Contenedor.builder()
            .peso(dto.getPeso())
            .volumen(dto.getVolumen())
            .estado(estado)
            .cliente(cliente)
            .build();

        Contenedor guardado = contenedorRepo.save(entidad);
        return mapToDto(guardado);
    }

    @Transactional
    public ContenedorDto actualizar(Long id, ActualizarContenedorDto dto) {
        // 1. Obtenemos el contenedor existente
        Contenedor entidad = contenedorRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                "Contenedor no encontrado con id " + id));

        // 2. Verificamos el cliente
        Cliente cliente = clienteRepo.findById(dto.getClienteId())
            .orElseThrow(() -> new EntityNotFoundException(
                "Cliente no encontrado con id " + dto.getClienteId()));

        // 3. Actualizamos los campos
        entidad.setPeso(dto.getPeso());
        entidad.setVolumen(dto.getVolumen());
        entidad.setEstado(dto.getEstado());
        entidad.setCliente(cliente);

        // 4. Guardamos y devolvemos
        Contenedor actualizado = contenedorRepo.save(entidad);
        return mapToDto(actualizado);
    }

    @Transactional(readOnly = true)
    public List<ContenedorDto> listar() {
        return contenedorRepo.findAll()
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ContenedorDto obtenerPorId(Long id) {
        Contenedor entidad = contenedorRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                "Contenedor no encontrado con id " + id));
        return mapToDto(entidad);
    }

    @Transactional(readOnly = true)
    public List<ContenedorDto> listarPorEstado(Estado estado) {
        return contenedorRepo.findByEstado(estado)
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public void eliminar(Long id) {
        if (!contenedorRepo.existsById(id)) {
            throw new EntityNotFoundException("Contenedor no encontrado con id " + id);
        }
        contenedorRepo.deleteById(id);
    }

    // Helper para convertir entidad → DTO
    private ContenedorDto mapToDto(Contenedor c) {
        return ContenedorDto.builder()
            .id(c.getId())
            .peso(c.getPeso())
            .volumen(c.getVolumen())
            .estado(c.getEstado())
            .clienteId(c.getCliente().getId())
            .build();
    }
}
