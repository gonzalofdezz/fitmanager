package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.EjercicioRutina;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EjercicioRutinaRepositoryOutputPort {

    EjercicioRutina guardar(EjercicioRutina ejercicio);

    Optional<EjercicioRutina> obtenerPorId(UUID id);

    List<EjercicioRutina> listarPorRutina(UUID rutinaId);

    void eliminar(UUID id);

    void eliminarPorRutina(UUID rutinaId);
}

