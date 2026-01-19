package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.Athlete;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AthleteRepositoryPort {
    Athlete save(Athlete athlete);
    Optional<Athlete> findById(UUID id);
    List<Athlete> findAll();
}
