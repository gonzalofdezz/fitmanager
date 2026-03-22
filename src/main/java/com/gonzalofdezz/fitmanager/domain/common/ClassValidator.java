package com.gonzalofdezz.fitmanager.domain.common;

import com.gonzalofdezz.fitmanager.domain.entity.GymClass;
import org.springframework.stereotype.Component;

@Component
public class ClassValidator {

    public void validate(GymClass gymClass) {
        if (gymClass == null) {
            throw new IllegalArgumentException("GymClass cannot be null");
        }

        if (gymClass.nombre() == null || gymClass.nombre().isBlank()) {
            throw new IllegalArgumentException("Class name cannot be empty");
        }

        if (gymClass.duracionMinutos() == null || gymClass.duracionMinutos() <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0");
        }

        if (gymClass.capacidadPorDefecto() == null || gymClass.capacidadPorDefecto() <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }

        if (gymClass.nivel() == null || gymClass.nivel().isBlank()) {
            throw new IllegalArgumentException("Level cannot be empty");
        }
    }
}

