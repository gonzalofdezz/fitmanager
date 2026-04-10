package com.gonzalofdezz.fitmanager.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record EditarClaseDTO(
        @NotBlank(message = "nombre no puede estar vacío")
        String nombre,
        @NotBlank(message = "descripcion no puede estar vacía")
        String descripcion,
        @NotBlank(message = "nivel no puede estar vacío")
        String nivel,
        @Positive(message = "duracionMinutos debe ser mayor a 0")
        Integer duracionMinutos,
        @Positive(message = "capacidadPorDefecto debe ser mayor a 0")
        Integer capacidadPorDefecto
) {
}

