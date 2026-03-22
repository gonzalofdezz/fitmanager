package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import java.time.LocalDateTime;
import java.util.UUID;

public interface CrearReservaUseCase {
    Reserva crear(UUID usuarioId, Long claseId, LocalDateTime fechaReserva);
}

