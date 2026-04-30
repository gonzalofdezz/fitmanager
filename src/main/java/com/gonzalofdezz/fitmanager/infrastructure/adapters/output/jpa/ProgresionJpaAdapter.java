package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.ProgresionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Progresion;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.ProgresionSpringDataRepository;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.ProgresionJpaMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProgresionJpaAdapter implements ProgresionRepositoryOutputPort {

    private final ProgresionSpringDataRepository progresionRepository;
    private final ProgresionJpaMapper progresionMapper;

    public ProgresionJpaAdapter(ProgresionSpringDataRepository progresionRepository, ProgresionJpaMapper progresionMapper) {
        this.progresionRepository = progresionRepository;
        this.progresionMapper = progresionMapper;
    }

    @Override
    public Progresion guardar(Progresion progresion) {
        var jpa = progresionMapper.toJpa(progresion);
        var guardada = progresionRepository.save(jpa);
        return progresionMapper.toDomain(guardada);
    }

    @Override
    public Optional<Progresion> obtenerPorId(UUID id) {
        return progresionRepository.findById(id).map(progresionMapper::toDomain);
    }

    @Override
    public Optional<Progresion> obtenerPorUsuarioId(UUID usuarioId) {
        return progresionRepository.findByUsuarioId(usuarioId).map(progresionMapper::toDomain);
    }

    @Override
    public void eliminar(UUID id) {
        progresionRepository.deleteById(id);
    }
}

