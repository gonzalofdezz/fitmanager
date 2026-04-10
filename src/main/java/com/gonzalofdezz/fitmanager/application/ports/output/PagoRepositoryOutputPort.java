package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.Pago;
import java.util.Optional;
import java.util.UUID;

public interface PagoRepositoryOutputPort {
    Pago guardar(Pago pago);
    Optional<Pago> obtenerPorId(UUID pagoId);
    Optional<Pago> obtenerPorSuscripcionId(UUID suscripcionId);
}

