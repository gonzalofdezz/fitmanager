package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.domain.entity.GymClass;

import java.util.List;
import java.util.Optional;

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

    /**
     * Creates a new gym class
     */
    GymClass crearClase(String nombre, String descripcion, String nivel, Integer duracionMinutos, Integer capacidadPorDefecto);

    /**
     * Edits an existing gym class
     */
    GymClass editarClase(Long id, String nombre, String descripcion, String nivel, Integer duracionMinutos, Integer capacidadPorDefecto);
}

