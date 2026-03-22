package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import java.util.Optional;
import java.util.UUID;

public interface SuscripcionesInputPort {
    /**
     * Obtiene la suscripción de un usuario
     * @param usuarioId ID del usuario
     * @return La suscripción del usuario si existe
     */
    Optional<Suscripcion> obtenerSuscripcion(UUID usuarioId);
}

