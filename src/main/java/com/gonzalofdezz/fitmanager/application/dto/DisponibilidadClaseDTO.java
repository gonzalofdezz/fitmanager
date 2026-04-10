package com.gonzalofdezz.fitmanager.application.dto;

public record DisponibilidadClaseDTO(
        Long claseId,
        String nombreClase,
        Integer capacidadTotal,
        Integer reservasActuales,
        Integer lugaresDisponibles,
        Boolean disponible
) {
}

