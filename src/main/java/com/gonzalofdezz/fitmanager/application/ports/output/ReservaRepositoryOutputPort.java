package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservaRepositoryOutputPort {
    Reserva guardar(Reserva reserva);

    Optional<Reserva> obtenerPorId(UUID id);

    List<Reserva> listarPorUsuario(UUID usuarioId);

    Optional<Reserva> obtenerPorIdConEstado(UUID id, String estado);

    void eliminar(UUID id);

    List<Reserva> listarPorClaseId(Long claseId);
}

