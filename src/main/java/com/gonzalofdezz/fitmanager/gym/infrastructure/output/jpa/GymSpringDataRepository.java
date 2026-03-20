package com.gonzalofdezz.fitmanager.gym.infrastructure.output.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GymSpringDataRepository extends JpaRepository<GymJpaEntity, Long> {
}