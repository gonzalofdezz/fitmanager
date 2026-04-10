package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository;

import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.ReservaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservaSpringDataRepository extends JpaRepository<ReservaJpaEntity, UUID> {
    List<ReservaJpaEntity> findByUsuarioId(UUID usuarioId);
    List<ReservaJpaEntity> findByClaseId(Long claseId);
}

