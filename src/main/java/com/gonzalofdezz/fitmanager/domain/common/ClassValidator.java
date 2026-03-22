package com.gonzalofdezz.fitmanager.domain.common;

import com.gonzalofdezz.fitmanager.domain.entity.GymClass;
import org.springframework.stereotype.Component;

@Component
public class ClassValidator {

    public void validate(GymClass gymClass) {
        if (gymClass == null) {
            throw new IllegalArgumentException("GymClass cannot be null");
        }

        if (gymClass.name() == null || gymClass.name().isBlank()) {
            throw new IllegalArgumentException("Class name cannot be empty");
        }

        if (gymClass.durationMinutes() == null || gymClass.durationMinutes() <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0");
        }

        if (gymClass.defaultCapacity() == null || gymClass.defaultCapacity() <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        if (gymClass.level() == null || gymClass.level().isBlank()) {
            throw new IllegalArgumentException("Level cannot be empty");
        }
    }
}

