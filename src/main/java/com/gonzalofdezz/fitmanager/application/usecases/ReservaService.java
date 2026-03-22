package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.ports.output.ReservaRepositoryPort;
import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReservaService implements CrearReservaUseCase {

    private final ReservaRepositoryPort reservaRepositoryPort;

    public ReservaService(ReservaRepositoryPort reservaRepositoryPort) {
        this.reservaRepositoryPort = reservaRepositoryPort;
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
        return reservaRepositoryPort.guardar(reserva);
    }
}

