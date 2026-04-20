package com.gonzalofdezz.fitmanager.application.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CrearInscripcionDTO(
        @NotNull UUID usuarioId,
        @NotNull Long claseId
) {
}

