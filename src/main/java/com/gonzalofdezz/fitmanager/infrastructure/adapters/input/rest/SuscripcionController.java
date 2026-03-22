package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.SuscripcionResponseDTO;
import com.gonzalofdezz.fitmanager.application.usecases.ObtenerSuscripcionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/suscripciones")
@Tag(name = "Suscripciones")
public class SuscripcionController {

    private final ObtenerSuscripcionUseCase obtenerSuscripcionUseCase;

    public SuscripcionController(ObtenerSuscripcionUseCase obtenerSuscripcionUseCase) {
        this.obtenerSuscripcionUseCase = obtenerSuscripcionUseCase;
    }

    @GetMapping("/{usuarioId}")
    @Operation(summary = "Obtiene la información de la suscripción de un usuario")
    public ResponseEntity<SuscripcionResponseDTO> obtenerSuscripcion(@PathVariable UUID usuarioId) {
        return obtenerSuscripcionUseCase.obtenerPorUsuarioId(usuarioId)
                .map(suscripcion -> new SuscripcionResponseDTO(
                        suscripcion.id(),
                        suscripcion.usuarioId(),
                        suscripcion.tipoPlan(),
                        suscripcion.fechaInicio(),
                        suscripcion.fechaFin(),
                        suscripcion.activa(),
                        suscripcion.fechaCreacion()
                ))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}

