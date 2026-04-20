package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDate;

public record ClassResponseDTO(
        Long id,
        String nombre,
        String descripcion,
        String nivel,
        Integer duracionMinutos,
        Integer capacidadPorDefecto,
        String diaSemana,
        LocalDate fecha
) {
}
