package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.SuscripcionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.SuscripcionSpringDataRepository;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.SuscripcionJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SuscripcionJpaAdapterOutput implements SuscripcionRepositoryOutputPort {

    private final SuscripcionSpringDataRepository suscripcionSpringDataRepository;
    private final SuscripcionJpaMapper suscripcionJpaMapper;

    public SuscripcionJpaAdapterOutput(SuscripcionSpringDataRepository suscripcionSpringDataRepository, SuscripcionJpaMapper suscripcionJpaMapper) {
        this.suscripcionSpringDataRepository = suscripcionSpringDataRepository;
        this.suscripcionJpaMapper = suscripcionJpaMapper;
    }

    @Override
    public Optional<Suscripcion> obtenerPorUsuarioId(UUID usuarioId) {
        return suscripcionSpringDataRepository.findByUsuarioId(usuarioId).map(suscripcionJpaMapper::toDomain);
    }

    @Override
    public Suscripcion guardar(Suscripcion suscripcion) {
        var jpa = suscripcionJpaMapper.toEntity(suscripcion);
        var guardada = suscripcionSpringDataRepository.save(jpa);
        return suscripcionJpaMapper.toDomain(guardada);
    }

    @Override
    public Optional<Suscripcion> obtenerPorId(UUID suscripcionId) {
        return suscripcionSpringDataRepository.findById(suscripcionId).map(suscripcionJpaMapper::toDomain);
    }

    @Override
    public List<Suscripcion> listarPorUsuario(UUID usuarioId) {
        return suscripcionSpringDataRepository.findAllByUsuarioId(usuarioId)
                .stream()
                .map(suscripcionJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(UUID suscripcionId) {
        suscripcionSpringDataRepository.deleteById(suscripcionId);
    }
}

