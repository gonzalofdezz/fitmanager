package com.gonzalofdezz.fitmanager.gym.infrastructure.input.rest.dto;

import com.gonzalofdezz.fitmanager.gym.domain.enums.GymPlan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateGymRequest {

    @NotBlank
    @Size(max = 120)
    private String name;

    @NotNull
    private GymPlan plan;

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
}