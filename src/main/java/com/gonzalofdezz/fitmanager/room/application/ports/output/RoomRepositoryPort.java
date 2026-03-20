package com.gonzalofdezz.fitmanager.room.application.ports.output;

import com.gonzalofdezz.fitmanager.room.domain.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepositoryPort {

    Room save(Room room);

    List<Room> findAll();

    Optional<Room> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}