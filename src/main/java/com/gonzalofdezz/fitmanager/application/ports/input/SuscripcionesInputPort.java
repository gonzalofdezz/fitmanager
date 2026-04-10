package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SuscripcionesInputPort {
    /**
     * Obtiene la suscripción de un usuario
     * @param usuarioId ID del usuario
     * @return La suscripción del usuario si existe
     */
    Optional<Suscripcion> obtenerSuscripcion(UUID usuarioId);

    /**
     * Crea una nueva suscripción para un usuario
     */
    Suscripcion crearSuscripcion(UUID usuarioId, String tipoPlan, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Anula la suscripción de un usuario
     */
    Suscripcion anularSuscripcion(UUID suscripcionId);

    /**
     * Renueva la suscripción de un usuario
     */
    Suscripcion renovarSuscripcion(UUID suscripcionId, String tipoPlan, LocalDateTime fechaFin);

    /**
     * Actualiza el tipo de plan de una suscripción
     */
    Suscripcion actualizarPlan(UUID suscripcionId, String nuevoTipoPlan);

    /**
     * Lista todas las suscripciones de un usuario
     */
    List<Suscripcion> listarPorUsuario(UUID usuarioId);
}

