package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository;

import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.MedicionProgresionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MedicionProgresionSpringDataRepository extends JpaRepository<MedicionProgresionJpaEntity, UUID> {
    List<MedicionProgresionJpaEntity> findByUsuarioIdOrderByFechaDesc(UUID usuarioId);
    List<MedicionProgresionJpaEntity> findByUsuarioIdAndFechaBetweenOrderByFechaAsc(UUID usuarioId, LocalDate desde, LocalDate hasta);
}

