package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "progresion")
public class ProgresionJpaEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private UUID usuarioId;

    @Column(name = "peso_corporal", columnDefinition = "NUMERIC(5, 2)")
    private Double pesoCorporal;

    @Column(nullable = false, name = "entrenamientos_completados")
    private Integer entrenamientosCompletados;

    @Column(nullable = false, name = "racha_asistencias")
    private Integer rachaAsistencias;

    @Column(name = "ultima_actividad")
    private LocalDateTime ultimaActividad;

    @Column(name = "bandejas_conseguidas")
    private String banderasConseguidas;

    @Column(name = "peso_maximo_levantado", columnDefinition = "NUMERIC(8, 2)")
    private Double pesoMaximoLevantado;

    @Column(nullable = false, name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public ProgresionJpaEntity() {
    }

    public ProgresionJpaEntity(UUID id, UUID usuarioId, Double pesoCorporal, Integer entrenamientosCompletados,
                              Integer rachaAsistencias, LocalDateTime ultimaActividad, String banderasConseguidas,
                              Double pesoMaximoLevantado, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.pesoCorporal = pesoCorporal;
        this.entrenamientosCompletados = entrenamientosCompletados;
        this.rachaAsistencias = rachaAsistencias;
        this.ultimaActividad = ultimaActividad;
        this.banderasConseguidas = banderasConseguidas;
        this.pesoMaximoLevantado = pesoMaximoLevantado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

    public Double getPesoCorporal() { return pesoCorporal; }
    public void setPesoCorporal(Double pesoCorporal) { this.pesoCorporal = pesoCorporal; }

    public Integer getEntrenamientosCompletados() { return entrenamientosCompletados; }
    public void setEntrenamientosCompletados(Integer entrenamientosCompletados) { this.entrenamientosCompletados = entrenamientosCompletados; }

    public Integer getRachaAsistencias() { return rachaAsistencias; }
    public void setRachaAsistencias(Integer rachaAsistencias) { this.rachaAsistencias = rachaAsistencias; }

    public LocalDateTime getUltimaActividad() { return ultimaActividad; }
    public void setUltimaActividad(LocalDateTime ultimaActividad) { this.ultimaActividad = ultimaActividad; }

    public String getBanderasConseguidas() { return banderasConseguidas; }
    public void setBanderasConseguidas(String banderasConseguidas) { this.banderasConseguidas = banderasConseguidas; }

    public Double getPesoMaximoLevantado() { return pesoMaximoLevantado; }
    public void setPesoMaximoLevantado(Double pesoMaximoLevantado) { this.pesoMaximoLevantado = pesoMaximoLevantado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}

