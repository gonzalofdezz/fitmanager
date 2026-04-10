package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository;

import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.UsuarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioSpringDataRepository extends JpaRepository<UsuarioJpaEntity, UUID> {
}

