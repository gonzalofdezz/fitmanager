package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String nombre,
        String email,
        Boolean activo,
        LocalDateTime fechaCreacion,
        String rol
) {
}

