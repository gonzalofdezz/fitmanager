package com.gonzalofdezz.fitmanager.application.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record RenovarSuscripcionDTO(
        @NotBlank(message = "suscripcionId no puede estar vacío")
        String suscripcionId,
        @NotBlank(message = "tipoPlan no puede estar vacío")
        String tipoPlan,
        LocalDateTime fechaFin
) {
}

