package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import java.time.LocalDateTime;
import java.util.UUID;

public interface ReservasInputPort {
    /**
     * Crea una nueva reserva
     * @param usuarioId ID del usuario
     * @param claseId ID de la clase
     * @param fechaReserva Fecha de la reserva
     * @return La reserva creada
     */
    Reserva crearReserva(UUID usuarioId, Long claseId, LocalDateTime fechaReserva);
}

