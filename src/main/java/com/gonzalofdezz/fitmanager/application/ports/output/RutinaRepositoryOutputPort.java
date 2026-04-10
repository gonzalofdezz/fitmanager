package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.EjercicioRutina;
import com.gonzalofdezz.fitmanager.domain.entity.Rutina;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RutinaRepositoryOutputPort {

    Rutina guardar(Rutina rutina);

    Optional<Rutina> obtenerPorId(UUID id);

    List<Rutina> listarPorUsuario(UUID usuarioId);

    void eliminar(UUID id);

    List<Rutina> obtenerTodas();
}

