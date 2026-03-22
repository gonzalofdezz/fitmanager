package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import java.util.Optional;
import java.util.UUID;

public interface ReservaRepositoryOutputPort {
    Reserva guardar(Reserva reserva);
    Optional<Reserva> obtenerPorId(UUID id);
}

