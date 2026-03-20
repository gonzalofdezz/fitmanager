package com.gonzalofdezz.fitmanager.gym.infrastructure.output.jpa;

import com.gonzalofdezz.fitmanager.gym.application.ports.output.GymRepositoryPort;
import com.gonzalofdezz.fitmanager.gym.domain.entity.Gym;
import com.gonzalofdezz.fitmanager.gym.infrastructure.mapper.GymMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GymJpaAdapter implements GymRepositoryPort {

    private final GymSpringDataRepository repository;
    private final GymMapper mapper;

    public GymJpaAdapter(GymSpringDataRepository repository, GymMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Gym save(Gym gym) {
        GymJpaEntity saved = repository.save(mapper.toJpa(gym));
        return mapper.toDomain(saved);
    }

    @Override
    public List<Gym> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Gym> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
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