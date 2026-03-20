package com.gonzalofdezz.fitmanager.gym.infrastructure.output.jpa;

import com.gonzalofdezz.fitmanager.gym.domain.enums.GymPlan;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gyms")
public class GymJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private GymPlan plan;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected GymJpaEntity() {
    }

    public GymJpaEntity(Long id, String name, GymPlan plan, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.plan = plan;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GymPlan getPlan() {
        return plan;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}