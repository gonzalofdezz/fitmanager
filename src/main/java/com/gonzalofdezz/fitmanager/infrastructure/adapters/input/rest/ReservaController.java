package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.CrearReservaDTO;
import com.gonzalofdezz.fitmanager.application.dto.ReservaResponseDTO;
import com.gonzalofdezz.fitmanager.application.usecases.CrearReservaUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
@Tag(name = "Reservas")
public class ReservaController {

    private final CrearReservaUseCase crearReservaUseCase;

    public ReservaController(CrearReservaUseCase crearReservaUseCase) {
        this.crearReservaUseCase = crearReservaUseCase;
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
}

