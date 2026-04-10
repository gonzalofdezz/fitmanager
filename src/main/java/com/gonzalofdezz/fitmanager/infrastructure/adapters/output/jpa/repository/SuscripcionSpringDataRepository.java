package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository;

import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.SuscripcionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SuscripcionSpringDataRepository extends JpaRepository<SuscripcionJpaEntity, UUID> {
    Optional<SuscripcionJpaEntity> findByUsuarioId(UUID usuarioId);
    List<SuscripcionJpaEntity> findAllByUsuarioId(UUID usuarioId);
}

