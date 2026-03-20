package com.gonzalofdezz.fitmanager.room.domain.entity;

public class Room {

    private Long id;
    private Long gymId;
    private String name;
    private Integer capacity;

    public Room() {
    }

    public Room(Long id, Long gymId, String name, Integer capacity) {
        this.id = id;
        this.gymId = gymId;
        this.name = name;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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