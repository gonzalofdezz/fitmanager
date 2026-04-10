package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "rutinas")
public class RutinaJpaEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID usuarioId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean activa;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    public RutinaJpaEntity() {
    }

    public RutinaJpaEntity(UUID id, UUID usuarioId, String nombre, String descripcion, Boolean activa, LocalDateTime fechaCreacion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activa = activa;
        this.fechaCreacion = fechaCreacion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}

