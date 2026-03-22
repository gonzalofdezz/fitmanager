package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository;

import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.ClassTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassTypeRepository extends JpaRepository<ClassTypeJpaEntity, Long> {
}
