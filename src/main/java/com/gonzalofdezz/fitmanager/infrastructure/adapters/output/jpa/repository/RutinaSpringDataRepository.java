package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository;

import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.RutinaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RutinaSpringDataRepository extends JpaRepository<RutinaJpaEntity, UUID> {
    List<RutinaJpaEntity> findByUsuarioId(UUID usuarioId);
}

