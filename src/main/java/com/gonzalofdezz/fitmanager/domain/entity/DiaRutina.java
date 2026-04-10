package com.gonzalofdezz.fitmanager.domain.entity;

import java.util.UUID;

public record DiaRutina(
        UUID id,
        UUID rutinaId,
        String dia,
        String ejerciciosDescripcion
) {
}

