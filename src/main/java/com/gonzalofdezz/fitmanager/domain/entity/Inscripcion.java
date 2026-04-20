package com.gonzalofdezz.fitmanager.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Inscripcion(
        UUID id,
        UUID usuarioId,
        Long claseId,
        LocalDateTime fechaInscripcion
) {
}

