package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SuscripcionRepositoryOutputPort {
    Optional<Suscripcion> obtenerPorUsuarioId(UUID usuarioId);

    Suscripcion guardar(Suscripcion suscripcion);

    Optional<Suscripcion> obtenerPorId(UUID suscripcionId);

    List<Suscripcion> listarPorUsuario(UUID usuarioId);

    void eliminar(UUID suscripcionId);
}

