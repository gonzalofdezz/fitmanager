package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProgresionResponseDTO(
        UUID id,
        UUID usuarioId,
        Double pesoCorporal,
        Integer entrenamientosCompletados,
        Integer rachaAsistencias,
        LocalDateTime ultimaActividad,
        String banderasConseguidas,
        Double pesoMaximoLevantado,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion
) {
}

