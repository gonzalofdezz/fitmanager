package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.ports.input.CreateAthleteUseCase;
import com.gonzalofdezz.fitmanager.application.ports.input.GetAthleteUseCase;
import com.gonzalofdezz.fitmanager.application.ports.output.AthleteRepositoryPort;
import com.gonzalofdezz.fitmanager.domain.entity.Athlete;
import com.gonzalofdezz.fitmanager.domain.exception.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public class AthleteService implements CreateAthleteUseCase, GetAthleteUseCase {

    private final AthleteRepositoryPort athleteRepository;

    public AthleteService(AthleteRepositoryPort athleteRepository) {
        this.athleteRepository = athleteRepository;
    }

    @Override
    public Athlete create(String name, Integer age) {
        Athlete athlete = new Athlete(UUID.randomUUID(), name, age);
        return athleteRepository.save(athlete);
    }

    @Override
    public Athlete getById(UUID id) {
        return athleteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Athlete not found: " + id));
    }

    @Override
    public List<Athlete> findAll() {
        return athleteRepository.findAll();
    }
}
