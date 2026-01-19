package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.domain.entity.Athlete;

import java.util.List;
import java.util.UUID;

public interface GetAthleteUseCase {
    Athlete getById(UUID id);
    List<Athlete> findAll();
}
