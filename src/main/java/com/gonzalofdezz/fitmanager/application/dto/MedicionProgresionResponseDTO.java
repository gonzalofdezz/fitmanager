package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record MedicionProgresionResponseDTO(
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

