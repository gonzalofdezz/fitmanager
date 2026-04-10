package com.gonzalofdezz.fitmanager.application.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record EditarRutinaDTO(
        @NotBlank(message = "nombre no puede estar vacío")
        String nombre,
        @NotBlank(message = "descripcion no puede estar vacía")
        String descripcion,
        Boolean activa
) {
}

