package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.CrearInscripcionDTO;
import com.gonzalofdezz.fitmanager.application.dto.InscripcionResponseDTO;
import com.gonzalofdezz.fitmanager.application.ports.input.InscripcionesInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inscripciones")
@Tag(name = "Inscripciones a Clases")
public class InscripcionController {

    private final InscripcionesInputPort inscripcionesInputPort;

    public InscripcionController(InscripcionesInputPort inscripcionesInputPort) {
        this.inscripcionesInputPort = inscripcionesInputPort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Inscribir usuario en una clase del gimnasio")
    public InscripcionResponseDTO crearInscripcion(@Valid @RequestBody CrearInscripcionDTO request) {
        var inscripcion = inscripcionesInputPort.crearInscripcion(request.usuarioId(), request.claseId());
        return new InscripcionResponseDTO(
                inscripcion.id(),
                inscripcion.usuarioId(),
                inscripcion.claseId(),
                inscripcion.fechaInscripcion()
        );
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Obtener todas las inscripciones a clases de un usuario")
    public List<InscripcionResponseDTO> obtenerInscripcionesPorUsuario(@PathVariable UUID usuarioId) {
        return inscripcionesInputPort.obtenerInscripcionesPorUsuario(usuarioId)
                .stream()
                .map(inscripcion -> new InscripcionResponseDTO(
                        inscripcion.id(),
                        inscripcion.usuarioId(),
                        inscripcion.claseId(),
                        inscripcion.fechaInscripcion()
                ))
                .toList();
    }

    @DeleteMapping("/{inscripcionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Cancelar inscripción a una clase")
    public void cancelarInscripcion(@PathVariable UUID inscripcionId) {
        inscripcionesInputPort.cancelarInscripcion(inscripcionId);
    }
}

