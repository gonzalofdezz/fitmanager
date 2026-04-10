package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "dias_rutina")
public class DiaRutinaJpaEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID rutinaId;

    @Column(nullable = false)
    private String dia;

    @Column
    private String ejerciciosDescripcion;

    public DiaRutinaJpaEntity() {
    }

    public DiaRutinaJpaEntity(UUID id, UUID rutinaId, String dia, String ejerciciosDescripcion) {
        this.id = id;
        this.rutinaId = rutinaId;
        this.dia = dia;
        this.ejerciciosDescripcion = ejerciciosDescripcion;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRutinaId() {
        return rutinaId;
    }

    public void setRutinaId(UUID rutinaId) {
        this.rutinaId = rutinaId;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getEjerciciosDescripcion() {
        return ejerciciosDescripcion;
    }

    public void setEjerciciosDescripcion(String ejerciciosDescripcion) {
        this.ejerciciosDescripcion = ejerciciosDescripcion;
    }
}

