package com.gonzalofdezz.fitmanager.gym.infrastructure.output.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GymSpringDataRepository extends JpaRepository<GymJpaEntity, UUID> {
}
