package com.gonzalofdezz.fitmanager.domain.entity;

import java.util.UUID;

public record EjercicioRutina(
        UUID id,
        UUID rutinaId,
        String nombreEjercicio,
        Integer series,
        Integer repeticiones,
        Double peso,
        Integer descansoSegundos
) {
}

