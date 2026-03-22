package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "class_types")
public class ClassTypeJpaEntity {

    @Id
    private Long id;

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

    public Long getId() {
        return id;
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
}
