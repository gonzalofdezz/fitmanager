package com.gonzalofdezz.fitmanager.gym.application.ports.output;

import com.gonzalofdezz.fitmanager.gym.domain.entity.Gym;

import java.util.List;
import java.util.Optional;

public interface GymRepositoryPort {

    Gym save(Gym gym);

    List<Gym> findAll();

    Optional<Gym> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}