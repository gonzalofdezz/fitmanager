package com.gonzalofdezz.fitmanager.application.dto;

import com.gonzalofdezz.fitmanager.application.dto.validation.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CrearSuscripcionDTO(
        @NotBlank(message = "usuarioId no puede estar vacío")
        @ValidUUID(message = "usuarioId debe ser un UUID válido")
        String usuarioId,
        @NotBlank(message = "tipoPlan no puede estar vacío")
        String tipoPlan,
        @NotNull(message = "fechaInicio no puede ser nulo")
        LocalDateTime fechaInicio,
        @NotNull(message = "fechaFin no puede ser nulo")
        LocalDateTime fechaFin
) {
}

