package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository;

import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.ProgresionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProgresionSpringDataRepository extends JpaRepository<ProgresionJpaEntity, UUID> {
    Optional<ProgresionJpaEntity> findByUsuarioId(UUID usuarioId);
}

