package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.*;

@Entity
@Table(name = "class_types")
public class ClassTypeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gym_id", nullable = false)
    private Long gymId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String level;

    @Column(name = "duration_min", nullable = false)
    private Integer durationMinutes;

    @Column(name = "default_capacity", nullable = false)
    private Integer defaultCapacity;

    public ClassTypeJpaEntity() {
    }

    public Long getId() {
        return id;
    }

    public Long getGymId() {
        return gymId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLevel() {
        return level;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public Integer getDefaultCapacity() {
        return defaultCapacity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setDefaultCapacity(Integer defaultCapacity) {
        this.defaultCapacity = defaultCapacity;
    }
}
