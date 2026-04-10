package com.gonzalofdezz.fitmanager.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CrearRutinaDTO(
        @NotNull(message = "usuarioId no puede ser nulo")
        UUID usuarioId,
        @NotBlank(message = "nombre no puede estar vacío")
        String nombre,
        @NotBlank(message = "descripcion no puede estar vacía")
        String descripcion
) {
}

