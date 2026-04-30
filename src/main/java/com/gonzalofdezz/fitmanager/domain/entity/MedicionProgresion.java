package com.gonzalofdezz.fitmanager.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record MedicionProgresion(
        UUID id,
        UUID usuarioId,
        Double peso,
        Double pesoMaximoLevantado,
        LocalDate fecha,
        String notas,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaActualizacion
) {
}

