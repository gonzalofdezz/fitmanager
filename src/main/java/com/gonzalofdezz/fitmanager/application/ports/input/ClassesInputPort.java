package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.domain.entity.GymClass;

import java.util.List;

public interface ClassesInputPort {

    /**
     * Retrieves all available gym classes
     * @return List of all gym classes
     */
    List<GymClass> getAllClasses();

    /**
     * Retrieves a gym class by its ID
     * @param id the class ID
     * @return the gym class if found
     */
    GymClass getClassById(Long id);
}

