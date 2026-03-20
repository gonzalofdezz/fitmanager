package com.gonzalofdezz.fitmanager.room.infrastructure.output.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class RoomJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gym_id", nullable = false)
    private Long gymId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    protected RoomJpaEntity() {
    }

    public RoomJpaEntity(Long id, Long gymId, String name, Integer capacity) {
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