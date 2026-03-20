package com.gonzalofdezz.fitmanager.room.infrastructure.output.jpa;

import com.gonzalofdezz.fitmanager.room.application.ports.output.RoomRepositoryPort;
import com.gonzalofdezz.fitmanager.room.domain.entity.Room;
import com.gonzalofdezz.fitmanager.room.infrastructure.mapper.RoomMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoomJpaAdapter implements RoomRepositoryPort {

    private final RoomSpringDataRepository repository;
    private final RoomMapper mapper;

    public RoomJpaAdapter(RoomSpringDataRepository repository, RoomMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Room save(Room room) {
        return mapper.toDomain(repository.save(mapper.toJpa(room)));
    }

    @Override
    public List<Room> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Room> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}