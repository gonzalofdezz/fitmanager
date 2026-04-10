package com.gonzalofdezz.fitmanager.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

public record CrearEjercicioRutinaDTO(
        @NotBlank(message = "nombreEjercicio no puede estar vacío")
        String nombreEjercicio,
        @NotNull @Positive(message = "series debe ser mayor a 0")
        Integer series,
        @NotNull @Positive(message = "repeticiones debe ser mayor a 0")
        Integer repeticiones,
        @Positive(message = "peso debe ser mayor a 0")
        Double peso,
        @Positive(message = "descansoSegundos debe ser mayor a 0")
        Integer descansoSegundos
) {
}

