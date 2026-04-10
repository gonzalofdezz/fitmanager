package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.application.dto.ConfirmarPagoDTO;
import com.gonzalofdezz.fitmanager.application.dto.PagoResponseDTO;
import com.gonzalofdezz.fitmanager.domain.entity.Pago;
import java.util.UUID;

public interface PagosInputPort {
    PagoResponseDTO iniciarPago(UUID usuarioId, String plan);
    Pago confirmarPago(ConfirmarPagoDTO request);
    Pago obtenerPago(UUID pagoId);
}

