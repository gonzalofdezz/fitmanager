package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pagos")
public class PagoJpaEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID suscripcionId;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String metodoPago;

    @Column
    private LocalDateTime fechaPago;

    @Column
    private String referenciaPago;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    public PagoJpaEntity() {}

    public PagoJpaEntity(UUID id, UUID suscripcionId, Double monto, String estado, String metodoPago, LocalDateTime fechaPago, String referenciaPago, LocalDateTime fechaCreacion) {
        this.id = id;
        this.suscripcionId = suscripcionId;
        this.monto = monto;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.referenciaPago = referenciaPago;
        this.fechaCreacion = fechaCreacion;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getSuscripcionId() { return suscripcionId; }
    public void setSuscripcionId(UUID suscripcionId) { this.suscripcionId = suscripcionId; }
    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }
    public String getReferenciaPago() { return referenciaPago; }
    public void setReferenciaPago(String referenciaPago) { this.referenciaPago = referenciaPago; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}

