package com.gonzalofdezz.fitmanager.domain.entity;

public record GymClass(
        Long id,
        String name,
        String description,
        String level,
        Integer durationMinutes,
        Integer defaultCapacity
) {
}
