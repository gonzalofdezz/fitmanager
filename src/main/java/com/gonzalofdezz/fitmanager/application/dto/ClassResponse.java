package com.gonzalofdezz.fitmanager.application.dto;

public record ClassResponse(
        Long id,
        String name,
        String description,
        String level,
        Integer durationMinutes,
        Integer defaultCapacity
) {
}
