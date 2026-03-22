package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest.dtos;

public record ClassRestDTO(
        Long id,
        String name,
        String description,
        String level,
        Integer durationMinutes,
        Integer defaultCapacity
) {
}

