package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.Progresion;
import java.util.Optional;
import java.util.UUID;

public interface ProgresionRepositoryOutputPort {

    Progresion guardar(Progresion progresion);

    Optional<Progresion> obtenerPorId(UUID id);

    Optional<Progresion> obtenerPorUsuarioId(UUID usuarioId);

    void eliminar(UUID id);
}

