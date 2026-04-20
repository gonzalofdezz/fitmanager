package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inscripciones")
public class InscripcionJpaEntity {

    @Id
    private UUID id;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "clase_id", nullable = false)
    private Long claseId;

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDateTime fechaInscripcion;

    public InscripcionJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public Long getClaseId() {
        return claseId;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setClaseId(Long claseId) {
        this.claseId = claseId;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
}

