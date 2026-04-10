package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.*;
import com.gonzalofdezz.fitmanager.application.ports.input.ReservasInputPort;
import com.gonzalofdezz.fitmanager.application.usecases.CrearReservaUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservas")
@Tag(name = "Reservas")
public class ReservaController {

    private final CrearReservaUseCase crearReservaUseCase;
    private final ReservasInputPort reservasInputPort;

    public ReservaController(CrearReservaUseCase crearReservaUseCase, ReservasInputPort reservasInputPort) {
        this.crearReservaUseCase = crearReservaUseCase;
        this.reservasInputPort = reservasInputPort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crea una nueva reserva para una clase")
    public ReservaResponseDTO crearReserva(@Valid @RequestBody CrearReservaDTO request) {
        var reserva = crearReservaUseCase.crear(
                request.usuarioId(),
                request.claseId(),
                request.fechaReserva()
        );

        return new ReservaResponseDTO(
                reserva.id(),
                reserva.usuarioId(),
                reserva.claseId(),
                reserva.fechaReserva(),
                reserva.fechaCreacion()
        );
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Lista todas las reservas de un usuario")
    public List<ListarReservasUsuarioDTO> listarPorUsuario(@PathVariable UUID usuarioId) {
        return reservasInputPort.listarPorUsuario(usuarioId)
                .stream()
                .map(r -> new ListarReservasUsuarioDTO(
                        r.id(),
                        r.usuarioId(),
                        r.claseId(),
                        r.fechaReserva(),
                        "ACTIVA",
                        r.fechaCreacion()
                ))
                .toList();
    }

    @DeleteMapping("/{reservaId}")
    @Operation(summary = "Cancela una reserva existente")
    public ResponseEntity<Void> cancelarReserva(@PathVariable UUID reservaId) {
        reservasInputPort.cancelarReserva(reservaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clases/{claseId}/disponibilidad")
    @Operation(summary = "Obtiene la disponibilidad de una clase")
    public DisponibilidadClaseDTO obtenerDisponibilidad(@PathVariable Long claseId) {
        return reservasInputPort.obtenerDisponibilidad(claseId);
    }
}

