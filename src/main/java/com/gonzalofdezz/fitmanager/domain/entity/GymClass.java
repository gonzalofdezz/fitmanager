package com.gonzalofdezz.fitmanager.domain.entity;

import java.time.LocalDate;

public record GymClass(
        Long id,
        String nombre,
        String descripcion,
        String nivel,
        Integer duracionMinutos,
        Integer capacidadPorDefecto,
        String diaSemana,
        LocalDate fecha,
        Long gymId
) {
}
