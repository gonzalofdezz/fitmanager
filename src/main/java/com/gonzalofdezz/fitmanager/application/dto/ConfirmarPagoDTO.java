package com.gonzalofdezz.fitmanager.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ConfirmarPagoDTO(
        @NotNull(message = "pagoId no puede ser nulo")
        UUID pagoId,
        @NotNull(message = "estado no puede ser nulo")
        String estado
) {
}

