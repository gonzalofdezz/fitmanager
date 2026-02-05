package com.gonzalofdezz.fitmanager.gym.domain.entity;

import com.gonzalofdezz.fitmanager.gym.domain.enums.GymPlan;

import java.time.LocalDateTime;
import java.util.UUID;

public class Gym {

    private UUID id;
    private String name;
    private GymPlan plan;
    private LocalDateTime createdAt;

    public Gym() {}

    public Gym(UUID id, String name, GymPlan plan, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.plan = plan;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public GymPlan getPlan() { return plan; }
    public void setPlan(GymPlan plan) { this.plan = plan; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
