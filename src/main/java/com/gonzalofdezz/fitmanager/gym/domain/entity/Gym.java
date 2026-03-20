package com.gonzalofdezz.fitmanager.gym.domain.entity;

import com.gonzalofdezz.fitmanager.gym.domain.enums.GymPlan;

import java.time.LocalDateTime;

public class Gym {

    private Long id;
    private String name;
    private GymPlan plan;
    private LocalDateTime createdAt;

    public Gym() {
    }

    public Gym(Long id, String name, GymPlan plan, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.plan = plan;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GymPlan getPlan() {
        return plan;
    }

    public void setPlan(GymPlan plan) {
        this.plan = plan;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}