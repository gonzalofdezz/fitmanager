package com.gonzalofdezz.fitmanager.room.infrastructure.mapper;

import com.gonzalofdezz.fitmanager.room.domain.entity.Room;
import com.gonzalofdezz.fitmanager.room.infrastructure.output.jpa.RoomJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomJpaEntity toJpa(Room room) {
        return new RoomJpaEntity(
                room.getId(),
                room.getGymId(),
                room.getName(),
                room.getCapacity()
        );
    }

    public Room toDomain(RoomJpaEntity entity) {
        return new Room(
                entity.getId(),
                entity.getGymId(),
                entity.getName(),
                entity.getCapacity()
        );
    }
}