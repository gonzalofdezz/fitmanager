package com.gonzalofdezz.fitmanager.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Suscripcion(
        UUID id,
        UUID usuarioId,
        String tipoPlan,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        Boolean activa,
        LocalDateTime fechaCreacion
) {
}

