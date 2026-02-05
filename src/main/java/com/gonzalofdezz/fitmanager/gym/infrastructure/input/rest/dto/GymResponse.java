package com.gonzalofdezz.fitmanager.gym.infrastructure.input.rest.dto;

import com.gonzalofdezz.fitmanager.gym.domain.enums.GymPlan;

import java.time.LocalDateTime;
import java.util.UUID;

public class GymResponse {
    private UUID id;
    private String name;
    private GymPlan plan;
    private LocalDateTime createdAt;

    public GymResponse(UUID id, String name, GymPlan plan, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.plan = plan;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public GymPlan getPlan() { return plan; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
