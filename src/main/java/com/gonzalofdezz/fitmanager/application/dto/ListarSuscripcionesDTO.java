package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ListarSuscripcionesDTO(
        UUID id,
        UUID usuarioId,
        String tipoPlan,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        Boolean activa,
        String estado,
        LocalDateTime fechaCreacion
) {
}

