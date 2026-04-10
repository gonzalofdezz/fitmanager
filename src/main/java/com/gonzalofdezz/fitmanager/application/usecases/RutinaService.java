package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.ports.input.RutinasInputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.RutinaRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.EjercicioRutinaRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.EjercicioRutina;
import com.gonzalofdezz.fitmanager.domain.entity.Rutina;
import com.gonzalofdezz.fitmanager.domain.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RutinaService implements RutinasInputPort {

    private final RutinaRepositoryOutputPort rutinaRepository;
    private final EjercicioRutinaRepositoryOutputPort ejercicioRepository;

    public RutinaService(RutinaRepositoryOutputPort rutinaRepository,
                        EjercicioRutinaRepositoryOutputPort ejercicioRepository) {
        this.rutinaRepository = rutinaRepository;
        this.ejercicioRepository = ejercicioRepository;
    }

    @Override
    public Rutina crearRutina(UUID usuarioId, String nombre, String descripcion) {
        Rutina rutina = new Rutina(
                UUID.randomUUID(),
                usuarioId,
                nombre,
                descripcion,
                true,
                LocalDateTime.now()
        );
        return rutinaRepository.guardar(rutina);
    }

    @Override
    public Rutina editarRutina(UUID rutinaId, String nombre, String descripcion, Boolean activa) {
        Rutina existente = rutinaRepository.obtenerPorId(rutinaId)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina no encontrada: " + rutinaId));

        Rutina actualizada = new Rutina(
                existente.id(),
                existente.usuarioId(),
                nombre,
                descripcion,
                activa != null ? activa : existente.activa(),
                existente.fechaCreacion()
        );
        return rutinaRepository.guardar(actualizada);
    }

    @Override
    public Rutina obtenerRutina(UUID rutinaId) {
        return rutinaRepository.obtenerPorId(rutinaId)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina no encontrada: " + rutinaId));
    }

    @Override
    public List<Rutina> listarPorUsuario(UUID usuarioId) {
        return rutinaRepository.listarPorUsuario(usuarioId);
    }

    @Override
    public void eliminarRutina(UUID rutinaId) {
        rutinaRepository.obtenerPorId(rutinaId)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina no encontrada: " + rutinaId));
        ejercicioRepository.eliminarPorRutina(rutinaId);
        rutinaRepository.eliminar(rutinaId);
    }

    @Override
    public EjercicioRutina agregarEjercicio(UUID rutinaId, String nombreEjercicio, Integer series, Integer repeticiones, Double peso, Integer descansoSegundos) {
        rutinaRepository.obtenerPorId(rutinaId)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina no encontrada: " + rutinaId));

        EjercicioRutina ejercicio = new EjercicioRutina(
                UUID.randomUUID(),
                rutinaId,
                nombreEjercicio,
                series,
                repeticiones,
                peso,
                descansoSegundos
        );
        return ejercicioRepository.guardar(ejercicio);
    }

    @Override
    public EjercicioRutina actualizarEjercicio(UUID ejercicioId, String nombreEjercicio, Integer series, Integer repeticiones, Double peso, Integer descansoSegundos) {
        EjercicioRutina existente = ejercicioRepository.obtenerPorId(ejercicioId)
                .orElseThrow(() -> new ResourceNotFoundException("Ejercicio no encontrado: " + ejercicioId));

        EjercicioRutina actualizado = new EjercicioRutina(
                ejercicioId,
                existente.rutinaId(),
                nombreEjercicio,
                series,
                repeticiones,
                peso,
                descansoSegundos
        );
        return ejercicioRepository.guardar(actualizado);
    }

    @Override
    public void eliminarEjercicio(UUID ejercicioId) {
        ejercicioRepository.obtenerPorId(ejercicioId)
                .orElseThrow(() -> new ResourceNotFoundException("Ejercicio no encontrado: " + ejercicioId));
        ejercicioRepository.eliminar(ejercicioId);
    }

    @Override
    public List<EjercicioRutina> listarEjerciciosPorRutina(UUID rutinaId) {
        rutinaRepository.obtenerPorId(rutinaId)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina no encontrada: " + rutinaId));
        return ejercicioRepository.listarPorRutina(rutinaId);
    }
}


