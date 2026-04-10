package com.gonzalofdezz.fitmanager.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Rutina(
        UUID id,
        UUID usuarioId,
        String nombre,
        String descripcion,
        Boolean activa,
        LocalDateTime fechaCreacion
) {
}

