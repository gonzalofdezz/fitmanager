package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository;

import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.InscripcionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InscripcionJpaRepository extends JpaRepository<InscripcionJpaEntity, UUID> {
    List<InscripcionJpaEntity> findByUsuarioId(UUID usuarioId);
}

