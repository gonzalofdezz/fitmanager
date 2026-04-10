package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.application.dto.DisponibilidadClaseDTO;
import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import java.time.LocalDateTime;
import java.util.List;
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

    /**
     * Lista todas las reservas de un usuario
     */
    List<Reserva> listarPorUsuario(UUID usuarioId);

    /**
     * Cancela una reserva existente
     */
    Reserva cancelarReserva(UUID reservaId);

    /**
     * Obtiene la disponibilidad de una clase
     */
    DisponibilidadClaseDTO obtenerDisponibilidad(Long claseId);
}

