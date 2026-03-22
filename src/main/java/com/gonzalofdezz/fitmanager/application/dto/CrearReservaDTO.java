package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CrearReservaDTO(
        UUID usuarioId,
        Long claseId,
        LocalDateTime fechaReserva
) {
}

