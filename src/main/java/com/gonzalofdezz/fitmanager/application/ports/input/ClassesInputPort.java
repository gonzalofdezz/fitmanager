package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.domain.entity.GymClass;

import java.util.List;

public interface ClassesInputPort {

    List<GymClass> getAllClasses();

    GymClass getClassById(Long id);

    GymClass crearClase(String nombre, String descripcion, String nivel, Integer duracionMinutos, Integer capacidadPorDefecto);

    GymClass editarClase(Long id, String nombre, String descripcion, String nivel, Integer duracionMinutos, Integer capacidadPorDefecto);

    void eliminarClase(Long id);
}

