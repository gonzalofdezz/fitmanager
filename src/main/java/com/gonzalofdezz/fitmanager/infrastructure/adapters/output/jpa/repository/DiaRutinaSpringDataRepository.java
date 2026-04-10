package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository;

import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.DiaRutinaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiaRutinaSpringDataRepository extends JpaRepository<DiaRutinaJpaEntity, UUID> {
    List<DiaRutinaJpaEntity> findByRutinaId(UUID rutinaId);
    void deleteByRutinaId(UUID rutinaId);
}

