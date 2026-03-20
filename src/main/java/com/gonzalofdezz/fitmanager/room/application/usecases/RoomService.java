package com.gonzalofdezz.fitmanager.room.application.usecases;

import com.gonzalofdezz.fitmanager.gym.application.ports.output.GymRepositoryPort;
import com.gonzalofdezz.fitmanager.room.application.ports.output.RoomRepositoryPort;
import com.gonzalofdezz.fitmanager.room.domain.entity.Room;

import java.util.List;

public class RoomService {

    private final RoomRepositoryPort roomRepository;
    private final GymRepositoryPort gymRepository;

    public RoomService(RoomRepositoryPort roomRepository, GymRepositoryPort gymRepository) {
        this.roomRepository = roomRepository;
        this.gymRepository = gymRepository;
    }

    public Room create(Long gymId, String name, Integer capacity) {
        if (!gymRepository.existsById(gymId)) {
            throw new RuntimeException("Gym not found with id: " + gymId);
        }

        Room room = new Room();
        room.setGymId(gymId);
        room.setName(name);
        room.setCapacity(capacity);

        return roomRepository.save(room);
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Room getById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    public Room update(Long id, Long gymId, String name, Integer capacity) {
        Room existing = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));

        if (!gymRepository.existsById(gymId)) {
            throw new RuntimeException("Gym not found with id: " + gymId);
        }

        existing.setGymId(gymId);
        existing.setName(name);
        existing.setCapacity(capacity);

        return roomRepository.save(existing);
    }

    public void delete(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Room not found with id: " + id);
        }
        roomRepository.deleteById(id);
    }
}