package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.ports.input.InscripcionesInputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.InscripcionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Inscripcion;
import com.gonzalofdezz.fitmanager.domain.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InscripcionService implements InscripcionesInputPort {

    private final InscripcionRepositoryOutputPort inscripcionRepositoryOutputPort;

    public InscripcionService(InscripcionRepositoryOutputPort inscripcionRepositoryOutputPort) {
        this.inscripcionRepositoryOutputPort = inscripcionRepositoryOutputPort;
    }

    @Override
    public Inscripcion crearInscripcion(UUID usuarioId, Long claseId) {
        Inscripcion inscripcion = new Inscripcion(
                UUID.randomUUID(),
                usuarioId,
                claseId,
                LocalDateTime.now()
        );
        return inscripcionRepositoryOutputPort.guardar(inscripcion);
    }

    @Override
    public List<Inscripcion> obtenerInscripcionesPorUsuario(UUID usuarioId) {
        return inscripcionRepositoryOutputPort.obtenerPorUsuarioId(usuarioId);
    }

    @Override
    public void cancelarInscripcion(UUID inscripcionId) {
        // Verificar que existe
        inscripcionRepositoryOutputPort.obtenerPorId(inscripcionId)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripción no encontrada: " + inscripcionId));
        // Eliminar
        inscripcionRepositoryOutputPort.eliminar(inscripcionId);
    }
}

