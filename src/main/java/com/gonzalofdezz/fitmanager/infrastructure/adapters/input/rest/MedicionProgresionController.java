package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.CrearMedicionDTO;
import com.gonzalofdezz.fitmanager.application.dto.MedicionProgresionResponseDTO;
import com.gonzalofdezz.fitmanager.application.usecases.MedicionProgresionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/mediciones-progresion")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MedicionProgresionController {
    private final MedicionProgresionService service;

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<MedicionProgresionResponseDTO> guardarMedicion(
            @PathVariable UUID usuarioId,
            @RequestBody CrearMedicionDTO dto
    ) {
        var resultado = service.guardar(usuarioId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MedicionProgresionResponseDTO>> obtenerMediciones(
            @PathVariable UUID usuarioId
    ) {
        var mediciones = service.obtenerPorUsuarioId(usuarioId);
        return ResponseEntity.ok(mediciones);
    }

    @GetMapping("/usuario/{usuarioId}/rango")
    public ResponseEntity<List<MedicionProgresionResponseDTO>> obtenerMedicionesEnRango(
            @PathVariable UUID usuarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta
    ) {
        var mediciones = service.obtenerPorRangoFechas(usuarioId, desde, hasta);
        return ResponseEntity.ok(mediciones);
    }
}

