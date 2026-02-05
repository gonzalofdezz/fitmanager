package com.gonzalofdezz.fitmanager.gym.infrastructure.output.jpa;

import com.gonzalofdezz.fitmanager.gym.application.ports.output.GymRepositoryPort;
import com.gonzalofdezz.fitmanager.gym.domain.entity.Gym;
import com.gonzalofdezz.fitmanager.gym.infrastructure.mapper.GymMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class GymJpaAdapter implements GymRepositoryPort {

    private final GymSpringDataRepository repository;
    private final GymMapper mapper = new GymMapper();

    public GymJpaAdapter(GymSpringDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Gym save(Gym gym) {
        GymJpaEntity saved = repository.save(mapper.toJpa(gym));
        return mapper.toDomain(saved);
    }

    @Override
    public List<Gym> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Gym> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

}
