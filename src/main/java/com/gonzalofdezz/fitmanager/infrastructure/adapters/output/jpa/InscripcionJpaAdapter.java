package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.InscripcionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Inscripcion;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.InscripcionJpaMapper;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.InscripcionJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class InscripcionJpaAdapter implements InscripcionRepositoryOutputPort {

    private final InscripcionJpaRepository inscripcionJpaRepository;
    private final InscripcionJpaMapper mapper;

    public InscripcionJpaAdapter(InscripcionJpaRepository inscripcionJpaRepository, InscripcionJpaMapper mapper) {
        this.inscripcionJpaRepository = inscripcionJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Inscripcion guardar(Inscripcion inscripcion) {
        var entity = mapper.toEntity(inscripcion);
        var saved = inscripcionJpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Inscripcion> obtenerPorId(UUID id) {
        return inscripcionJpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Inscripcion> obtenerPorUsuarioId(UUID usuarioId) {
        return inscripcionJpaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void eliminar(UUID id) {
        inscripcionJpaRepository.deleteById(id);
    }
}

