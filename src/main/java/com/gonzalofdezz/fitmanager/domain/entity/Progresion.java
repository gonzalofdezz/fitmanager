package com.gonzalofdezz.fitmanager.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Progresion(
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

