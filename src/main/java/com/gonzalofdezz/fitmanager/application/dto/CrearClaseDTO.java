package com.gonzalofdezz.fitmanager.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrearClaseDTO(
        @NotBlank(message = "nombre no puede estar vacío")
        String nombre,
        @NotBlank(message = "descripcion no puede estar vacía")
        String descripcion,
        @NotBlank(message = "nivel no puede estar vacío")
        String nivel,
        @NotNull @Positive(message = "duracionMinutos debe ser mayor a 0")
        Integer duracionMinutos,
        @NotNull @Positive(message = "capacidadPorDefecto debe ser mayor a 0")
        Integer capacidadPorDefecto
) {
}

