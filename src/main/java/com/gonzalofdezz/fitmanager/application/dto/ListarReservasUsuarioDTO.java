package com.gonzalofdezz.fitmanager.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ListarReservasUsuarioDTO(
        UUID id,
        UUID usuarioId,
        Long claseId,
        LocalDateTime fechaReserva,
        String estado,
        LocalDateTime fechaCreacion
) {
}

