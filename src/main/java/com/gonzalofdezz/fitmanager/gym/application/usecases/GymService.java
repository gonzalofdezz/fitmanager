package com.gonzalofdezz.fitmanager.gym.application.usecases;

import com.gonzalofdezz.fitmanager.gym.application.ports.output.GymRepositoryPort;
import com.gonzalofdezz.fitmanager.gym.domain.entity.Gym;
import com.gonzalofdezz.fitmanager.gym.domain.enums.GymPlan;

import java.time.LocalDateTime;
import java.util.List;

public class GymService {

    private final GymRepositoryPort repository;

    public GymService(GymRepositoryPort repository) {
        this.repository = repository;
    }

    public Gym create(String name, GymPlan plan) {
        Gym gym = new Gym();
        gym.setName(name);
        gym.setPlan(plan);
        gym.setCreatedAt(LocalDateTime.now());
        return repository.save(gym);
    }

    public List<Gym> getAll() {
        return repository.findAll();
    }

    public Gym getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gym not found with id: " + id));
    }

    public Gym update(Long id, String name, GymPlan plan) {
        Gym existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gym not found with id: " + id));

        existing.setName(name);
        existing.setPlan(plan);

        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Gym not found with id: " + id);
        }
        repository.deleteById(id);
    }
}