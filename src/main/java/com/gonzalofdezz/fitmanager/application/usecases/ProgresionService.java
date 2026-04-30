package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.dto.ActualizarProgresionDTO;
import com.gonzalofdezz.fitmanager.application.dto.ProgresionResponseDTO;
import com.gonzalofdezz.fitmanager.application.ports.output.ProgresionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Progresion;
import com.gonzalofdezz.fitmanager.domain.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProgresionService {

    private final ProgresionRepositoryOutputPort progresionRepository;

    public ProgresionService(ProgresionRepositoryOutputPort progresionRepository) {
        this.progresionRepository = progresionRepository;
    }

    public ProgresionResponseDTO crearProgresion(UUID usuarioId) {
        // Get-or-Create: si ya existe, retorna la existente; si no, crea una nueva
        return progresionRepository.obtenerPorUsuarioId(usuarioId)
                .map(this::convertirADTO)
                .orElseGet(() -> {
                    Progresion progresion = new Progresion(
                            UUID.randomUUID(),
                            usuarioId,
                            null,
                            0,
                            0,
                            LocalDateTime.now(),
                            "",
                            null,
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    );
                    return convertirADTO(progresionRepository.guardar(progresion));
                });
    }

    public ProgresionResponseDTO obtenerProgresionPorUsuario(UUID usuarioId) {
        // Get-or-Create: si no existe, crea una nueva automáticamente
        return progresionRepository.obtenerPorUsuarioId(usuarioId)
                .map(this::convertirADTO)
                .orElseGet(() -> crearProgresion(usuarioId));
    }

    public ProgresionResponseDTO actualizarProgresion(UUID usuarioId, ActualizarProgresionDTO dto) {
        Progresion existente = progresionRepository.obtenerPorUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Progresión no encontrada para usuario: " + usuarioId));

        Progresion actualizada = new Progresion(
                existente.id(),
                existente.usuarioId(),
                dto.pesoCorporal() != null ? dto.pesoCorporal() : existente.pesoCorporal(),
                dto.entrenamientosCompletados() != null ? dto.entrenamientosCompletados() : existente.entrenamientosCompletados(),
                dto.rachaAsistencias() != null ? dto.rachaAsistencias() : existente.rachaAsistencias(),
                LocalDateTime.now(),
                dto.banderasConseguidas() != null ? dto.banderasConseguidas() : existente.banderasConseguidas(),
                dto.pesoMaximoLevantado() != null ? dto.pesoMaximoLevantado() : existente.pesoMaximoLevantado(),
                existente.fechaCreacion(),
                LocalDateTime.now()
        );

        return convertirADTO(progresionRepository.guardar(actualizada));
    }

    public void incrementarEntrenamientos(UUID usuarioId) {
        Progresion progresion = progresionRepository.obtenerPorUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Progresión no encontrada para usuario: " + usuarioId));

        Progresion actualizada = new Progresion(
                progresion.id(),
                progresion.usuarioId(),
                progresion.pesoCorporal(),
                progresion.entrenamientosCompletados() + 1,
                progresion.rachaAsistencias() + 1,
                LocalDateTime.now(),
                progresion.banderasConseguidas(),
                progresion.pesoMaximoLevantado(),
                progresion.fechaCreacion(),
                LocalDateTime.now()
        );

        progresionRepository.guardar(actualizada);
    }

    private ProgresionResponseDTO convertirADTO(Progresion progresion) {
        return new ProgresionResponseDTO(
                progresion.id(),
                progresion.usuarioId(),
                progresion.pesoCorporal(),
                progresion.entrenamientosCompletados(),
                progresion.rachaAsistencias(),
                progresion.ultimaActividad(),
                progresion.banderasConseguidas(),
                progresion.pesoMaximoLevantado(),
                progresion.fechaCreacion(),
                progresion.fechaActualizacion()
        );
    }
}

