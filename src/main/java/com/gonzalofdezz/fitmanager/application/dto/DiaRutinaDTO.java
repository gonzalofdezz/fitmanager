package com.gonzalofdezz.fitmanager.application.dto;

import java.util.UUID;

public record DiaRutinaDTO(
        UUID id,
        UUID rutinaId,
        String dia,
        String ejerciciosDescripcion
) {
}

