package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.EjercicioRutinaRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.EjercicioRutina;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.EjercicioRutinaSpringDataRepository;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.EjercicioRutinaJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EjercicioRutinaJpaAdapter implements EjercicioRutinaRepositoryOutputPort {

    private final EjercicioRutinaSpringDataRepository ejercicioRepository;
    private final EjercicioRutinaJpaMapper ejercicioMapper;

    public EjercicioRutinaJpaAdapter(EjercicioRutinaSpringDataRepository ejercicioRepository,
                                     EjercicioRutinaJpaMapper ejercicioMapper) {
        this.ejercicioRepository = ejercicioRepository;
        this.ejercicioMapper = ejercicioMapper;
    }

    @Override
    public EjercicioRutina guardar(EjercicioRutina ejercicio) {
        var jpa = ejercicioMapper.toJpa(ejercicio);
        var guardado = ejercicioRepository.save(jpa);
        return ejercicioMapper.toDomain(guardado);
    }

    @Override
    public Optional<EjercicioRutina> obtenerPorId(UUID id) {
        return ejercicioRepository.findById(id).map(ejercicioMapper::toDomain);
    }

    @Override
    public List<EjercicioRutina> listarPorRutina(UUID rutinaId) {
        return ejercicioRepository.findByRutinaId(rutinaId)
                .stream()
                .map(ejercicioMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(UUID id) {
        ejercicioRepository.deleteById(id);
    }

    @Override
    public void eliminarPorRutina(UUID rutinaId) {
        ejercicioRepository.deleteByRutinaId(rutinaId);
    }
}

