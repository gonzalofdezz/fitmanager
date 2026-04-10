package com.gonzalofdezz.fitmanager.application.dto;

import java.util.UUID;

public record PagoResponseDTO(
        UUID pagoId,
        UUID suscripcionId,
        Double monto,
        String estado,
        String metodoPago,
        String urlPago,
        String mensaje
) {
}

