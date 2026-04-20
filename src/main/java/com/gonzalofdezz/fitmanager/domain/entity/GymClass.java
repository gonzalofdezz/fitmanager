package com.gonzalofdezz.fitmanager.domain.entity;

public record GymClass(
        Long id,
        String nombre,
        String descripcion,
        String nivel,
        Integer duracionMinutos,
        Integer capacidadPorDefecto,
        Long gymId
) {
}
