package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class UsuarioJpaEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    public UsuarioJpaEntity() {
    }

    public UsuarioJpaEntity(UUID id, String nombre, String email, Boolean activo, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}

