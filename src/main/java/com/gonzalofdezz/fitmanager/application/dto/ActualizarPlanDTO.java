package com.gonzalofdezz.fitmanager.application.dto;

import jakarta.validation.constraints.NotBlank;

public record ActualizarPlanDTO(
        @NotBlank(message = "tipoPlan no puede estar vacío")
        String tipoPlan
) {
}

