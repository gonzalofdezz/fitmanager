package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.SuscripcionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.SuscripcionSpringDataRepository;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.SuscripcionJpaMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

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
}

