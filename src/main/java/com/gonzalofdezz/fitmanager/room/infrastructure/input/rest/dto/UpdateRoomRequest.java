package com.gonzalofdezz.fitmanager.room.infrastructure.input.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateRoomRequest {

    @NotNull
    private Long gymId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    @Min(1)
    private Integer capacity;

    public Long getGymId() {
        return gymId;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}