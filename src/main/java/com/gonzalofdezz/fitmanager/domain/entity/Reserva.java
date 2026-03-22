package com.gonzalofdezz.fitmanager.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Reserva(
        UUID id,
        UUID usuarioId,
        Long claseId,
        LocalDateTime fechaReserva,
        LocalDateTime fechaCreacion
) {
}

