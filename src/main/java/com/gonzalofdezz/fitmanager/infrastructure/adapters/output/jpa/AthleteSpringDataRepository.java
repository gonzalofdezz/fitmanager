package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AthleteSpringDataRepository extends JpaRepository<AthleteJpaEntity, UUID> {
}
