package com.gonzalofdezz.fitmanager.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Pago(
        UUID id,
        UUID suscripcionId,
        Double monto,
        String estado,
        String metodoPago,
        LocalDateTime fechaPago,
        String referenciaPago,
        LocalDateTime fechaCreacion
) {
}

