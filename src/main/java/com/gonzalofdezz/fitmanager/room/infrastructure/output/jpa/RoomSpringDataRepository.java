package com.gonzalofdezz.fitmanager.room.infrastructure.output.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomSpringDataRepository extends JpaRepository<RoomJpaEntity, Long> {
}