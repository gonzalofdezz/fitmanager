package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.domain.entity.EjercicioRutina;
import com.gonzalofdezz.fitmanager.domain.entity.Rutina;
import java.util.List;
import java.util.UUID;

public interface RutinasInputPort {

    /**
     * Crea una nueva rutina para un usuario
     */
    Rutina crearRutina(UUID usuarioId, String nombre, String descripcion);

    /**
     * Edita una rutina existente
     */
    Rutina editarRutina(UUID rutinaId, String nombre, String descripcion, Boolean activa);

    /**
     * Obtiene una rutina por su ID
     */
    Rutina obtenerRutina(UUID rutinaId);

    /**
     * Lista todas las rutinas de un usuario
     */
    List<Rutina> listarPorUsuario(UUID usuarioId);

    /**
     * Elimina una rutina
     */
    void eliminarRutina(UUID rutinaId);

    /**
     * Agrega un ejercicio a una rutina
     */
    EjercicioRutina agregarEjercicio(UUID rutinaId, String nombreEjercicio, Integer series, Integer repeticiones, Double peso, Integer descansoSegundos);

    /**
     * Actualiza un ejercicio de una rutina
     */
    EjercicioRutina actualizarEjercicio(UUID ejercicioId, String nombreEjercicio, Integer series, Integer repeticiones, Double peso, Integer descansoSegundos);

    /**
     * Elimina un ejercicio de una rutina
     */
    void eliminarEjercicio(UUID ejercicioId);

    /**
     * Obtiene los ejercicios de una rutina
     */
    List<EjercicioRutina> listarEjerciciosPorRutina(UUID rutinaId);
}

