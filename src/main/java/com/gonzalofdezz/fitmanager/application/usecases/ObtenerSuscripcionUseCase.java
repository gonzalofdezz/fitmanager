package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import java.util.Optional;
import java.util.UUID;

public interface ObtenerSuscripcionUseCase {
    Optional<Suscripcion> obtenerPorUsuarioId(UUID usuarioId);
}

