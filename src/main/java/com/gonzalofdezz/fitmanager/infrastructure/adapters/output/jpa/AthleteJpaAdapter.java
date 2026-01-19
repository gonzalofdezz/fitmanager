package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.AthleteRepositoryPort;
import com.gonzalofdezz.fitmanager.domain.entity.Athlete;
import com.gonzalofdezz.fitmanager.infrastructure.mapper.AthletePersistenceMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AthleteJpaAdapter implements AthleteRepositoryPort {

    private final AthleteSpringDataRepository repository;
    private final AthletePersistenceMapper mapper;

    public AthleteJpaAdapter(AthleteSpringDataRepository repository, AthletePersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Athlete save(Athlete athlete) {
        AthleteJpaEntity saved = repository.save(mapper.toJpa(athlete));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Athlete> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Athlete> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }
}
