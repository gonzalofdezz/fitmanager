package com.gonzalofdezz.fitmanager.gym.infrastructure.input.rest.dto;

import com.gonzalofdezz.fitmanager.gym.domain.enums.GymPlan;


public class CreateGymRequest {
    private String name;
    private GymPlan plan;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public GymPlan getPlan() { return plan; }
    public void setPlan(GymPlan plan) { this.plan = plan; }
}
