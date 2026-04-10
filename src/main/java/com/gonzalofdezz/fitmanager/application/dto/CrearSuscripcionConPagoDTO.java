package com.gonzalofdezz.fitmanager.application.dto;

import jakarta.validation.constraints.NotNull;

public record CrearSuscripcionConPagoDTO(
        @NotNull(message = "usuarioId no puede ser nulo")
        String usuarioId,
        @NotNull(message = "plan no puede ser nulo")
        String plan
) {
}

