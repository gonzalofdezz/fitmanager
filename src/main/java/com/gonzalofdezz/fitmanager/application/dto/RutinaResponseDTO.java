package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record RutinaResponseDTO(
        UUID id,
        UUID usuarioId,
        String nombre,
        String descripcion,
        Boolean activa,
        LocalDateTime fechaCreacion
) {
}

