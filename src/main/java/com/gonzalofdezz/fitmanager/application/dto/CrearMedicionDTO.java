package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDate;

public record CrearMedicionDTO(
        Double peso,
        Double pesoMaximoLevantado,
        LocalDate fecha,
        String notas
) {
}

