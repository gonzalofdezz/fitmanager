package com.gonzalofdezz.fitmanager.application.dto;

import java.util.UUID;

public record EjercicioRutinaResponseDTO(
        UUID id,
        UUID rutinaId,
        String nombreEjercicio,
        Integer series,
        Integer repeticiones,
        Double peso,
        Integer descansoSegundos
) {
}

