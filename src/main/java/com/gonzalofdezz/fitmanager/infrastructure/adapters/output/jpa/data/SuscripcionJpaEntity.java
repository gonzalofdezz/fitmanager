package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "suscripciones")
public class SuscripcionJpaEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private UUID usuarioId;

    @Column(nullable = false)
    private String tipoPlan;

    @Column
    private LocalDateTime fechaInicio;

    @Column
    private LocalDateTime fechaFin;

    @Column(nullable = false)
    private Boolean activa;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    public SuscripcionJpaEntity() {
    }

    public SuscripcionJpaEntity(UUID id, UUID usuarioId, String tipoPlan, LocalDateTime fechaInicio, LocalDateTime fechaFin, Boolean activa, LocalDateTime fechaCreacion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.tipoPlan = tipoPlan;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activa = activa;
        this.fechaCreacion = fechaCreacion;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public Boolean getActiva() {
        return activa;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}

