package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.GymClass;

import java.util.List;

public interface LoadClassesOutputPort {

    List<GymClass> findAll();

    GymClass getClassById(Long id);

    GymClass save(GymClass gymClass);

    void deleteById(Long id);
}
