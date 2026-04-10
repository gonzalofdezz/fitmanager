package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.*;
import com.gonzalofdezz.fitmanager.application.ports.input.SuscripcionesInputPort;
import com.gonzalofdezz.fitmanager.application.usecases.ObtenerSuscripcionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/suscripciones")
@Tag(name = "Suscripciones")
public class SuscripcionController {

    private final ObtenerSuscripcionUseCase obtenerSuscripcionUseCase;
    private final SuscripcionesInputPort suscripcionesInputPort;

    public SuscripcionController(ObtenerSuscripcionUseCase obtenerSuscripcionUseCase,
                                SuscripcionesInputPort suscripcionesInputPort) {
        this.obtenerSuscripcionUseCase = obtenerSuscripcionUseCase;
        this.suscripcionesInputPort = suscripcionesInputPort;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crea una nueva suscripción")
    public SuscripcionResponseDTO crear(@Valid @RequestBody CrearSuscripcionDTO request) {
        var suscripcion = suscripcionesInputPort.crearSuscripcion(
                UUID.fromString(request.usuarioId()),
                request.tipoPlan(),
                request.fechaInicio(),
                request.fechaFin()
        );
        return new SuscripcionResponseDTO(
                suscripcion.id(),
                suscripcion.usuarioId(),
                suscripcion.tipoPlan(),
                suscripcion.fechaInicio(),
                suscripcion.fechaFin(),
                suscripcion.activa(),
                suscripcion.fechaCreacion()
        );
    }

    @DeleteMapping("/{suscripcionId}")
    @Operation(summary = "Anula una suscripción")
    public ResponseEntity<Void> anular(@PathVariable UUID suscripcionId) {
        suscripcionesInputPort.anularSuscripcion(suscripcionId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{suscripcionId}/renovar")
    @Operation(summary = "Renueva una suscripción")
    public SuscripcionResponseDTO renovar(@PathVariable UUID suscripcionId,
                                          @Valid @RequestBody RenovarSuscripcionDTO request) {
        var renovada = suscripcionesInputPort.renovarSuscripcion(
                suscripcionId,
                request.tipoPlan(),
                request.fechaFin()
        );
        return new SuscripcionResponseDTO(
                renovada.id(),
                renovada.usuarioId(),
                renovada.tipoPlan(),
                renovada.fechaInicio(),
                renovada.fechaFin(),
                renovada.activa(),
                renovada.fechaCreacion()
        );
    }

    @PutMapping("/{suscripcionId}/plan")
    @Operation(summary = "Actualiza el tipo de plan de una suscripción")
    public SuscripcionResponseDTO actualizarPlan(@PathVariable UUID suscripcionId,
                                                  @Valid @RequestBody ActualizarPlanDTO request) {
        var actualizada = suscripcionesInputPort.actualizarPlan(suscripcionId, request.tipoPlan());
        return new SuscripcionResponseDTO(
                actualizada.id(),
                actualizada.usuarioId(),
                actualizada.tipoPlan(),
                actualizada.fechaInicio(),
                actualizada.fechaFin(),
                actualizada.activa(),
                actualizada.fechaCreacion()
        );
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Lista todas las suscripciones de un usuario")
    public List<ListarSuscripcionesDTO> listarPorUsuario(@PathVariable UUID usuarioId) {
        return suscripcionesInputPort.listarPorUsuario(usuarioId)
                .stream()
                .map(s -> new ListarSuscripcionesDTO(
                        s.id(),
                        s.usuarioId(),
                        s.tipoPlan(),
                        s.fechaInicio(),
                        s.fechaFin(),
                        s.activa(),
                        s.activa() ? "ACTIVA" : "ANULADA",
                        s.fechaCreacion()
                ))
                .toList();
    }
}

