package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.domain.entity.Athlete;

public interface CreateAthleteUseCase {
    Athlete create(String name, Integer age);
}
