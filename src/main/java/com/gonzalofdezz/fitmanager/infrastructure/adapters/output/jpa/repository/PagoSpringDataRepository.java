package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository;

import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.PagoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PagoSpringDataRepository extends JpaRepository<PagoJpaEntity, UUID> {
    Optional<PagoJpaEntity> findBySuscripcionId(UUID suscripcionId);
}

