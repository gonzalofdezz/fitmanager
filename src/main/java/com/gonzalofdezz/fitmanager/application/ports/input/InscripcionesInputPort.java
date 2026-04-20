package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.domain.entity.Inscripcion;

import java.util.List;
import java.util.UUID;

public interface InscripcionesInputPort {
    Inscripcion crearInscripcion(UUID usuarioId, Long claseId);
    List<Inscripcion> obtenerInscripcionesPorUsuario(UUID usuarioId);
    void cancelarInscripcion(UUID inscripcionId);
}

