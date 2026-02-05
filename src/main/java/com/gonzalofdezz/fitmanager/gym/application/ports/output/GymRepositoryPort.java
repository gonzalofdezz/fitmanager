package com.gonzalofdezz.fitmanager.gym.application.ports.output;

import com.gonzalofdezz.fitmanager.gym.domain.entity.Gym;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GymRepositoryPort {
    Gym save(Gym gym);
    List<Gym> findAll();
    Optional<Gym> findById(UUID id);
    void deleteById(UUID id);
    boolean existsById(UUID id);

}
