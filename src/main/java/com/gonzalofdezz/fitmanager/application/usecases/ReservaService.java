package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.dto.DisponibilidadClaseDTO;
import com.gonzalofdezz.fitmanager.application.ports.input.ReservasInputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.ReservaRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.LoadClassesOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import com.gonzalofdezz.fitmanager.domain.entity.GymClass;
import com.gonzalofdezz.fitmanager.domain.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservaService implements CrearReservaUseCase, ReservasInputPort {

    private final ReservaRepositoryOutputPort reservaRepositoryOutputPort;
    private final LoadClassesOutputPort loadClassesOutputPort;

    public ReservaService(ReservaRepositoryOutputPort reservaRepositoryOutputPort,
                          LoadClassesOutputPort loadClassesOutputPort) {
        this.reservaRepositoryOutputPort = reservaRepositoryOutputPort;
        this.loadClassesOutputPort = loadClassesOutputPort;
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

    @Override
    public List<Reserva> listarPorUsuario(UUID usuarioId) {
        return reservaRepositoryOutputPort.listarPorUsuario(usuarioId);
    }

    @Override
    public Reserva cancelarReserva(UUID reservaId) {
        Reserva reserva = reservaRepositoryOutputPort.obtenerPorId(reservaId)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada: " + reservaId));

        Reserva cancelada = new Reserva(
                reserva.id(),
                reserva.usuarioId(),
                reserva.claseId(),
                reserva.fechaReserva(),
                reserva.fechaCreacion()
        );
        reservaRepositoryOutputPort.eliminar(reservaId);
        return cancelada;
    }

    @Override
    public DisponibilidadClaseDTO obtenerDisponibilidad(Long claseId) {
        GymClass gymClass = loadClassesOutputPort.getClassById(claseId);
        List<Reserva> reservas = reservaRepositoryOutputPort.listarPorClaseId(claseId);

        int lugaresDisponibles = gymClass.capacidadPorDefecto() - reservas.size();

        return new DisponibilidadClaseDTO(
                claseId,
                gymClass.nombre(),
                gymClass.capacidadPorDefecto(),
                reservas.size(),
                Math.max(0, lugaresDisponibles),
                lugaresDisponibles > 0
        );
    }
}

