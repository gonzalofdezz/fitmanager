package com.gonzalofdezz.fitmanager.room.infrastructure.input.rest.dto;

public class RoomResponse {

    private Long id;
    private Long gymId;
    private String name;
    private Integer capacity;

    public RoomResponse(Long id, Long gymId, String name, Integer capacity) {
        this.id = id;
        this.gymId = gymId;
        this.name = name;
        this.capacity = capacity;
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

    public Integer getCapacity() {
        return capacity;
    }
}