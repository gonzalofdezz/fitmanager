package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.dto.ConfirmarPagoDTO;
import com.gonzalofdezz.fitmanager.application.dto.PagoResponseDTO;
import com.gonzalofdezz.fitmanager.application.ports.input.PagosInputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.PagoRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.SuscripcionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Pago;
import com.gonzalofdezz.fitmanager.domain.entity.PlanType;
import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import com.gonzalofdezz.fitmanager.domain.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PagoService implements PagosInputPort {

    private final PagoRepositoryOutputPort pagoRepository;
    private final SuscripcionRepositoryOutputPort suscripcionRepository;

    public PagoService(PagoRepositoryOutputPort pagoRepository,
                      SuscripcionRepositoryOutputPort suscripcionRepository) {
        this.pagoRepository = pagoRepository;
        this.suscripcionRepository = suscripcionRepository;
    }

    @Override
    public PagoResponseDTO iniciarPago(UUID usuarioId, String plan) {
        try {
            PlanType planType = PlanType.valueOf(plan.toUpperCase());
            
            // Obtener o crear la suscripción del usuario
            var suscripcionExistente = suscripcionRepository.obtenerPorUsuarioId(usuarioId);

            LocalDateTime ahora = LocalDateTime.now();
            LocalDateTime fechaFin = ahora.plusDays(planType.getDias());
            
            Suscripcion suscripcion;
            if (suscripcionExistente.isPresent()) {
                // Si ya existe, actualizarla con el nuevo plan
                Suscripcion existente = suscripcionExistente.get();
                suscripcion = new Suscripcion(
                        existente.id(),
                        usuarioId,
                        plan,
                        ahora,
                        fechaFin,
                        false,
                        existente.fechaCreacion()
                );
            } else {
                // Si no existe, crear una nueva
                suscripcion = new Suscripcion(
                        UUID.randomUUID(),
                        usuarioId,
                        plan,
                        ahora,
                        fechaFin,
                        false,
                        ahora
                );
            }

            suscripcion = suscripcionRepository.guardar(suscripcion);
            
            // Crear pago en BD con estado PENDIENTE
            Pago pago = new Pago(
                    UUID.randomUUID(),
                    suscripcion.id(),
                    planType.getPrecio(),
                    "PENDIENTE",
                    "STRIPE_FAKE",
                    null,
                    "REF-" + UUID.randomUUID().toString().substring(0, 8),
                    ahora
            );
            
            pago = pagoRepository.guardar(pago);
            
            // Devolver respuesta con URL fake de pago
            return new PagoResponseDTO(
                    pago.id(),
                    suscripcion.id(),
                    pago.monto(),
                    pago.estado(),
                    pago.metodoPago(),
                    "http://localhost:8080/pagos/" + pago.id() + "/confirmar-fake",
                    "Pago iniciado. Usa /pagos/" + pago.id() + "/confirmar-fake para completar el pago en DESARROLLO"
            );
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Plan no válido: " + plan);
        }
    }

    @Override
    public Pago confirmarPago(ConfirmarPagoDTO request) {
        Pago pago = pagoRepository.obtenerPorId(request.pagoId())
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado: " + request.pagoId()));
        
        // Actualizar pago
        Pago pagoActualizado = new Pago(
                pago.id(),
                pago.suscripcionId(),
                pago.monto(),
                request.estado(),
                pago.metodoPago(),
                LocalDateTime.now(),
                pago.referenciaPago(),
                pago.fechaCreacion()
        );
        
        pagoRepository.guardar(pagoActualizado);
        
        // Si el pago fue exitoso, activar suscripción
        if ("COMPLETADO".equals(request.estado())) {
            Suscripcion suscripcion = suscripcionRepository.obtenerPorId(pago.suscripcionId())
                    .orElseThrow(() -> new ResourceNotFoundException("Suscripción no encontrada"));
            
            Suscripcion suscripcionActiva = new Suscripcion(
                    suscripcion.id(),
                    suscripcion.usuarioId(),
                    suscripcion.tipoPlan(),
                    suscripcion.fechaInicio(),
                    suscripcion.fechaFin(),
                    true,
                    suscripcion.fechaCreacion()
            );
            
            suscripcionRepository.guardar(suscripcionActiva);
        }
        
        return pagoActualizado;
    }

    @Override
    public Pago obtenerPago(UUID pagoId) {
        return pagoRepository.obtenerPorId(pagoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado: " + pagoId));
    }
}

