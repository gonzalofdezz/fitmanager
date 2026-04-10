package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.*;
import com.gonzalofdezz.fitmanager.application.ports.input.RutinasInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rutinas")
@Tag(name = "Rutinas")
public class RutinaController {

    private final RutinasInputPort rutinasInputPort;

    public RutinaController(RutinasInputPort rutinasInputPort) {
        this.rutinasInputPort = rutinasInputPort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crea una nueva rutina")
    public RutinaResponseDTO crear(@Valid @RequestBody CrearRutinaDTO request) {
        var rutina = rutinasInputPort.crearRutina(
                request.usuarioId(),
                request.nombre(),
                request.descripcion()
        );
        return new RutinaResponseDTO(
                rutina.id(),
                rutina.usuarioId(),
                rutina.nombre(),
                rutina.descripcion(),
                rutina.activa(),
                rutina.fechaCreacion()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una rutina por su ID")
    public RutinaResponseDTO obtener(@PathVariable UUID id) {
        var rutina = rutinasInputPort.obtenerRutina(id);
        return new RutinaResponseDTO(
                rutina.id(),
                rutina.usuarioId(),
                rutina.nombre(),
                rutina.descripcion(),
                rutina.activa(),
                rutina.fechaCreacion()
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita una rutina existente")
    public RutinaResponseDTO editar(@PathVariable UUID id, @Valid @RequestBody EditarRutinaDTO request) {
        var actualizada = rutinasInputPort.editarRutina(
                id,
                request.nombre(),
                request.descripcion(),
                request.activa()
        );
        return new RutinaResponseDTO(
                actualizada.id(),
                actualizada.usuarioId(),
                actualizada.nombre(),
                actualizada.descripcion(),
                actualizada.activa(),
                actualizada.fechaCreacion()
        );
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Lista todas las rutinas de un usuario")
    public List<RutinaResponseDTO> listarPorUsuario(@PathVariable UUID usuarioId) {
        return rutinasInputPort.listarPorUsuario(usuarioId)
                .stream()
                .map(r -> new RutinaResponseDTO(
                        r.id(),
                        r.usuarioId(),
                        r.nombre(),
                        r.descripcion(),
                        r.activa(),
                        r.fechaCreacion()
                ))
                .toList();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una rutina")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        rutinasInputPort.eliminarRutina(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{rutinaId}/ejercicios")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Agrega un ejercicio a una rutina")
    public EjercicioRutinaResponseDTO agregarEjercicio(@PathVariable UUID rutinaId,
                                                        @Valid @RequestBody CrearEjercicioRutinaDTO request) {
        var ejercicio = rutinasInputPort.agregarEjercicio(
                rutinaId,
                request.nombreEjercicio(),
                request.series(),
                request.repeticiones(),
                request.peso(),
                request.descansoSegundos()
        );
        return new EjercicioRutinaResponseDTO(
                ejercicio.id(),
                ejercicio.rutinaId(),
                ejercicio.nombreEjercicio(),
                ejercicio.series(),
                ejercicio.repeticiones(),
                ejercicio.peso(),
                ejercicio.descansoSegundos()
        );
    }

    @GetMapping("/{rutinaId}/ejercicios")
    @Operation(summary = "Lista todos los ejercicios de una rutina")
    public List<EjercicioRutinaResponseDTO> listarEjercicios(@PathVariable UUID rutinaId) {
        return rutinasInputPort.listarEjerciciosPorRutina(rutinaId)
                .stream()
                .map(e -> new EjercicioRutinaResponseDTO(
                        e.id(),
                        e.rutinaId(),
                        e.nombreEjercicio(),
                        e.series(),
                        e.repeticiones(),
                        e.peso(),
                        e.descansoSegundos()
                ))
                .toList();
    }

    @PutMapping("/ejercicios/{ejercicioId}")
    @Operation(summary = "Edita un ejercicio de una rutina")
    public EjercicioRutinaResponseDTO editarEjercicio(@PathVariable UUID ejercicioId,
                                                       @Valid @RequestBody CrearEjercicioRutinaDTO request) {
        var actualizado = rutinasInputPort.actualizarEjercicio(
                ejercicioId,
                request.nombreEjercicio(),
                request.series(),
                request.repeticiones(),
                request.peso(),
                request.descansoSegundos()
        );
        return new EjercicioRutinaResponseDTO(
                actualizado.id(),
                actualizado.rutinaId(),
                actualizado.nombreEjercicio(),
                actualizado.series(),
                actualizado.repeticiones(),
                actualizado.peso(),
                actualizado.descansoSegundos()
        );
    }

    @DeleteMapping("/ejercicios/{ejercicioId}")
    @Operation(summary = "Elimina un ejercicio de una rutina")
    public ResponseEntity<Void> eliminarEjercicio(@PathVariable UUID ejercicioId) {
        rutinasInputPort.eliminarEjercicio(ejercicioId);
        return ResponseEntity.noContent().build();
    }
}

