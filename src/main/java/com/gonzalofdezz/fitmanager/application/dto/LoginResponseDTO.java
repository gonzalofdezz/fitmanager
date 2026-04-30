package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LoginResponseDTO(
        UUID id,
        String nombre,
        String email,
        Boolean activo,
        LocalDateTime fechaCreacion,
        String rol,
        String token
) {
}

