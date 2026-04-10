package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.ConfirmarPagoDTO;
import com.gonzalofdezz.fitmanager.application.dto.CrearSuscripcionConPagoDTO;
import com.gonzalofdezz.fitmanager.application.dto.PagoResponseDTO;
import com.gonzalofdezz.fitmanager.application.ports.input.PagosInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pagos")
@Tag(name = "Pagos")
public class PagoController {

    private final PagosInputPort pagosInputPort;

    public PagoController(PagosInputPort pagosInputPort) {
        this.pagosInputPort = pagosInputPort;
    }

    @PostMapping("/iniciar")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Inicia un pago para una suscripción")
    public PagoResponseDTO iniciarPago(@Valid @RequestBody CrearSuscripcionConPagoDTO request) {
        return pagosInputPort.iniciarPago(
                UUID.fromString(request.usuarioId()),
                request.plan()
        );
    }

    @PostMapping("/confirmar")
    @Operation(summary = "Confirma un pago")
    public ResponseEntity<String> confirmarPago(@Valid @RequestBody ConfirmarPagoDTO request) {
        var pago = pagosInputPort.confirmarPago(request);
        return ResponseEntity.ok("Pago " + pago.estado() + " - Referencia: " + pago.referenciaPago());
    }

    @GetMapping("/{pagoId}/confirmar-fake")
    @Operation(summary = "Confirma un pago de prueba (SOLO DESARROLLO)")
    public ResponseEntity<String> confirmarPagoFake(@PathVariable UUID pagoId) {
        var pagoConfirmado = pagosInputPort.confirmarPago(
                new ConfirmarPagoDTO(pagoId, "COMPLETADO")
        );
        return ResponseEntity.ok("Pago confirmado exitosamente - Referencia: " + pagoConfirmado.referenciaPago());
    }

    @GetMapping("/{pagoId}")
    @Operation(summary = "Obtiene un pago")
    public ResponseEntity<String> obtenerPago(@PathVariable UUID pagoId) {
        var pago = pagosInputPort.obtenerPago(pagoId);
        return ResponseEntity.ok("Pago ID: " + pago.id() + " - Estado: " + pago.estado() + " - Monto: €" + pago.monto());
    }
}

