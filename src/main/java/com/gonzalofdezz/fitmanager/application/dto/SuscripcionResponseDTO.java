package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SuscripcionResponseDTO(
        UUID id,
        UUID usuarioId,
        String tipoPlan,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        Boolean activa,
        LocalDateTime fechaCreacion
) {
}

