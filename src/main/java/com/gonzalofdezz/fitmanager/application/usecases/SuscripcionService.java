package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.ports.input.SuscripcionesInputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.SuscripcionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import com.gonzalofdezz.fitmanager.domain.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SuscripcionService implements ObtenerSuscripcionUseCase, SuscripcionesInputPort {

    private final SuscripcionRepositoryOutputPort suscripcionRepositoryOutputPort;

    public SuscripcionService(SuscripcionRepositoryOutputPort suscripcionRepositoryOutputPort) {
        this.suscripcionRepositoryOutputPort = suscripcionRepositoryOutputPort;
    }

    @Override
    public Optional<Suscripcion> obtenerPorUsuarioId(UUID usuarioId) {
        return suscripcionRepositoryOutputPort.obtenerPorUsuarioId(usuarioId);
    }

    @Override
    public Optional<Suscripcion> obtenerSuscripcion(UUID usuarioId) {
        return obtenerPorUsuarioId(usuarioId);
    }

    @Override
    public Suscripcion crearSuscripcion(UUID usuarioId, String tipoPlan, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Suscripcion suscripcion = new Suscripcion(
                UUID.randomUUID(),
                usuarioId,
                tipoPlan,
                fechaInicio,
                fechaFin,
                true,
                LocalDateTime.now()
        );
        return suscripcionRepositoryOutputPort.guardar(suscripcion);
    }

    @Override
    public Suscripcion anularSuscripcion(UUID suscripcionId) {
        Suscripcion suscripcion = suscripcionRepositoryOutputPort.obtenerPorId(suscripcionId)
                .orElseThrow(() -> new ResourceNotFoundException("Suscripción no encontrada: " + suscripcionId));

        Suscripcion anulada = new Suscripcion(
                suscripcion.id(),
                suscripcion.usuarioId(),
                suscripcion.tipoPlan(),
                suscripcion.fechaInicio(),
                LocalDateTime.now(),
                false,
                suscripcion.fechaCreacion()
        );
        return suscripcionRepositoryOutputPort.guardar(anulada);
    }

    @Override
    public Suscripcion renovarSuscripcion(UUID suscripcionId, String tipoPlan, LocalDateTime fechaFin) {
        Suscripcion suscripcion = suscripcionRepositoryOutputPort.obtenerPorId(suscripcionId)
                .orElseThrow(() -> new ResourceNotFoundException("Suscripción no encontrada: " + suscripcionId));

        Suscripcion renovada = new Suscripcion(
                suscripcion.id(),
                suscripcion.usuarioId(),
                tipoPlan != null ? tipoPlan : suscripcion.tipoPlan(),
                LocalDateTime.now(),
                fechaFin,
                true,
                suscripcion.fechaCreacion()
        );
        return suscripcionRepositoryOutputPort.guardar(renovada);
    }

    @Override
    public Suscripcion actualizarPlan(UUID suscripcionId, String nuevoTipoPlan) {
        Suscripcion suscripcion = suscripcionRepositoryOutputPort.obtenerPorId(suscripcionId)
                .orElseThrow(() -> new ResourceNotFoundException("Suscripción no encontrada: " + suscripcionId));

        Suscripcion actualizada = new Suscripcion(
                suscripcion.id(),
                suscripcion.usuarioId(),
                nuevoTipoPlan,
                suscripcion.fechaInicio(),
                suscripcion.fechaFin(),
                suscripcion.activa(),
                suscripcion.fechaCreacion()
        );
        return suscripcionRepositoryOutputPort.guardar(actualizada);
    }

    @Override
    public List<Suscripcion> listarPorUsuario(UUID usuarioId) {
        return suscripcionRepositoryOutputPort.listarPorUsuario(usuarioId);
    }
}

