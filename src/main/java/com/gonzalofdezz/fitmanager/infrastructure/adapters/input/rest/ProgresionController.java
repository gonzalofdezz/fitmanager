package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.ActualizarProgresionDTO;
import com.gonzalofdezz.fitmanager.application.dto.ProgresionResponseDTO;
import com.gonzalofdezz.fitmanager.application.usecases.ProgresionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/progresion")
@Tag(name = "Progresión")
@CrossOrigin(origins = "*")
public class ProgresionController {

    private final ProgresionService progresionService;

    public ProgresionController(ProgresionService progresionService) {
        this.progresionService = progresionService;
    }

    @PostMapping("/usuario/{usuarioId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear progresión inicial para un usuario")
    public ProgresionResponseDTO crear(@PathVariable UUID usuarioId) {
        return progresionService.crearProgresion(usuarioId);
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Obtener progresión de un usuario")
    public ProgresionResponseDTO obtenerPorUsuario(@PathVariable UUID usuarioId) {
        return progresionService.obtenerProgresionPorUsuario(usuarioId);
    }

    @PutMapping("/usuario/{usuarioId}")
    @Operation(summary = "Actualizar progresión de un usuario")
    public ProgresionResponseDTO actualizar(
            @PathVariable UUID usuarioId,
            @Valid @RequestBody ActualizarProgresionDTO request) {
        return progresionService.actualizarProgresion(usuarioId, request);
    }

    @PostMapping("/usuario/{usuarioId}/completar-entrenamiento")
    @Operation(summary = "Incrementar entrenamientos completados")
    public ResponseEntity<Void> completarEntrenamiento(@PathVariable UUID usuarioId) {
        progresionService.incrementarEntrenamientos(usuarioId);
        return ResponseEntity.noContent().build();
    }
}

