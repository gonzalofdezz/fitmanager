package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservaResponseDTO(
        UUID id,
        UUID usuarioId,
        Long claseId,
        LocalDateTime fechaReserva,
        LocalDateTime fechaCreacion
) {
}

