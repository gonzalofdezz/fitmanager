package com.gonzalofdezz.fitmanager.application.ports;

import com.gonzalofdezz.fitmanager.application.ports.input.ClassesInputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.LoadClassesOutputPort;
import com.gonzalofdezz.fitmanager.application.usecases.GetClassesUseCase;
import com.gonzalofdezz.fitmanager.domain.entity.GymClass;
import com.gonzalofdezz.fitmanager.domain.common.ResourceNotFoundException;

import java.util.List;

public class GetClassesService implements GetClassesUseCase, ClassesInputPort {

    private final LoadClassesOutputPort loadClassesOutputPort;

    public GetClassesService(LoadClassesOutputPort loadClassesPort) {
        this.loadClassesOutputPort = loadClassesPort;
    }

    @Override
    public List<GymClass> execute() {
        return loadClassesOutputPort.findAll();
    }

    @Override
    public List<GymClass> getAllClasses() {
        return execute();
    }

    @Override
    public GymClass getClassById(Long id) {
        return loadClassesOutputPort.getClassById(id);
    }

    @Override
    public GymClass crearClase(String nombre, String descripcion, String nivel, Integer duracionMinutos, Integer capacidadPorDefecto) {
        // Por ahora usamos gym_id = 1 (el gym por defecto creado en la migración V2)
        // En el futuro, esto debería venir del contexto del usuario autenticado
        GymClass gymClass = new GymClass(
                null,
                nombre,
                descripcion,
                nivel,
                duracionMinutos,
                capacidadPorDefecto,
                1L  // gym_id por defecto
        );
        return loadClassesOutputPort.save(gymClass);
    }

    @Override
    public GymClass editarClase(Long id, String nombre, String descripcion, String nivel, Integer duracionMinutos, Integer capacidadPorDefecto) {
        GymClass existente = loadClassesOutputPort.getClassById(id);

        GymClass actualizada = new GymClass(
                existente.id(),
                nombre,
                descripcion,
                nivel,
                duracionMinutos,
                capacidadPorDefecto,
                existente.gymId()  // Mantener el gym_id original
        );
        return loadClassesOutputPort.save(actualizada);
    }
}
