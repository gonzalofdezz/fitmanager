package com.gonzalofdezz.fitmanager.gym.infrastructure.input.rest.dto;

import com.gonzalofdezz.fitmanager.gym.domain.enums.GymPlan;

import java.time.LocalDateTime;

public class GymResponse {

    private Long id;
    private String name;
    private GymPlan plan;
    private LocalDateTime createdAt;

    public GymResponse(Long id, String name, GymPlan plan, LocalDateTime createdAt) {
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