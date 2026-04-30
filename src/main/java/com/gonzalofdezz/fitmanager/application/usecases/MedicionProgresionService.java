package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.dto.CrearMedicionDTO;
import com.gonzalofdezz.fitmanager.application.dto.MedicionProgresionResponseDTO;
import com.gonzalofdezz.fitmanager.application.ports.output.MedicionProgresionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.UsuarioRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.common.ResourceNotFoundException;
import com.gonzalofdezz.fitmanager.domain.entity.MedicionProgresion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicionProgresionService {
    private final MedicionProgresionRepositoryOutputPort repository;
    private final UsuarioRepositoryOutputPort usuarioRepository;

    public MedicionProgresionResponseDTO guardar(UUID usuarioId, CrearMedicionDTO dto) {
        // Validar que el usuario existe antes de intentar insertar
        if (usuarioRepository.findById(usuarioId).isEmpty()) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + usuarioId);
        }
        var medicion = new MedicionProgresion(
                UUID.randomUUID(),
                usuarioId,
                dto.peso(),
                dto.pesoMaximoLevantado(),
                dto.fecha() != null ? dto.fecha() : LocalDate.now(),
                dto.notas(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        var guardada = repository.guardar(medicion);
        return toDTO(guardada);
    }

    public List<MedicionProgresionResponseDTO> obtenerPorUsuarioId(UUID usuarioId) {
        return repository.obtenerPorUsuarioId(usuarioId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<MedicionProgresionResponseDTO> obtenerPorRangoFechas(UUID usuarioId, LocalDate desde, LocalDate hasta) {
        return repository.obtenerPorUsuarioYFechas(usuarioId, desde, hasta)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private MedicionProgresionResponseDTO toDTO(MedicionProgresion medicion) {
        return new MedicionProgresionResponseDTO(
                medicion.id(),
                medicion.usuarioId(),
                medicion.peso(),
                medicion.pesoMaximoLevantado(),
                medicion.fecha(),
                medicion.notas(),
                medicion.fechaCreacion(),
                medicion.fechaActualizacion()
        );
    }
}

