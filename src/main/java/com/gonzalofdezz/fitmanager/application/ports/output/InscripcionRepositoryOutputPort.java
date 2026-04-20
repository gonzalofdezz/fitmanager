package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.Inscripcion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InscripcionRepositoryOutputPort {
    Inscripcion guardar(Inscripcion inscripcion);
    Optional<Inscripcion> obtenerPorId(UUID id);
    List<Inscripcion> obtenerPorUsuarioId(UUID usuarioId);
    void eliminar(UUID id);
}

