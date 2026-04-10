package com.gonzalofdezz.fitmanager.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Usuario(
        UUID id,
        String nombre,
        String email,
        Boolean activo,
        LocalDateTime fechaCreacion
) {
}

