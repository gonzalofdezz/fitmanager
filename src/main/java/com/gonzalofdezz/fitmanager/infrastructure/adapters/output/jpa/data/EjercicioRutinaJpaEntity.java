package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ejercicios_rutina")
public class EjercicioRutinaJpaEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID rutinaId;

    @Column(nullable = false)
    private String nombreEjercicio;

    @Column(nullable = false)
    private Integer series;

    @Column(nullable = false)
    private Integer repeticiones;

    @Column
    private Double peso;

    @Column
    private Integer descansoSegundos;

    public EjercicioRutinaJpaEntity() {
    }

    public EjercicioRutinaJpaEntity(UUID id, UUID rutinaId, String nombreEjercicio, Integer series, Integer repeticiones, Double peso, Integer descansoSegundos) {
        this.id = id;
        this.rutinaId = rutinaId;
        this.nombreEjercicio = nombreEjercicio;
        this.series = series;
        this.repeticiones = repeticiones;
        this.peso = peso;
        this.descansoSegundos = descansoSegundos;
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

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(Integer repeticiones) {
        this.repeticiones = repeticiones;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getDescansoSegundos() {
        return descansoSegundos;
    }

    public void setDescansoSegundos(Integer descansoSegundos) {
        this.descansoSegundos = descansoSegundos;
    }
}

