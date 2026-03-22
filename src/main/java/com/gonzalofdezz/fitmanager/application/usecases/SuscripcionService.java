package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.ports.input.SuscripcionesInputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.SuscripcionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import org.springframework.stereotype.Service;

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
}

