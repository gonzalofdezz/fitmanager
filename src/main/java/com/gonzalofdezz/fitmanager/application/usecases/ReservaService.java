package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.ports.input.ReservasInputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.ReservaRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReservaService implements CrearReservaUseCase, ReservasInputPort {

    private final ReservaRepositoryOutputPort reservaRepositoryOutputPort;

    public ReservaService(ReservaRepositoryOutputPort reservaRepositoryOutputPort) {
        this.reservaRepositoryOutputPort = reservaRepositoryOutputPort;
    }

    @Override
    public Reserva crear(UUID usuarioId, Long claseId, LocalDateTime fechaReserva) {
        Reserva reserva = new Reserva(
                UUID.randomUUID(),
                usuarioId,
                claseId,
                fechaReserva,
                LocalDateTime.now()
        );
        return reservaRepositoryOutputPort.guardar(reserva);
    }

    @Override
    public Reserva crearReserva(UUID usuarioId, Long claseId, LocalDateTime fechaReserva) {
        return crear(usuarioId, claseId, fechaReserva);
    }
}

