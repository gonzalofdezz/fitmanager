package com.gonzalofdezz.fitmanager.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CrearSuscripcionDTO(
        @NotNull(message = "usuarioId no puede ser nulo")
        String usuarioId,
        @NotBlank(message = "tipoPlan no puede estar vacío")
        String tipoPlan,
        @NotNull(message = "fechaInicio no puede ser nulo")
        LocalDateTime fechaInicio,
        @NotNull(message = "fechaFin no puede ser nulo")
        LocalDateTime fechaFin
) {
}

