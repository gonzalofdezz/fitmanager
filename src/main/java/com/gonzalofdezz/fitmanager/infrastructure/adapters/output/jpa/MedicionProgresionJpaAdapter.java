package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.MedicionProgresionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.MedicionProgresion;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.MedicionProgresionJpaMapper;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.MedicionProgresionSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MedicionProgresionJpaAdapter implements MedicionProgresionRepositoryOutputPort {
    private final MedicionProgresionSpringDataRepository repository;
    private final MedicionProgresionJpaMapper mapper;

    @Override
    public MedicionProgresion guardar(MedicionProgresion medicion) {
        var entity = mapper.toEntity(medicion);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<MedicionProgresion> obtenerPorUsuarioId(UUID usuarioId) {
        return repository.findByUsuarioIdOrderByFechaDesc(usuarioId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicionProgresion> obtenerPorUsuarioYFechas(UUID usuarioId, LocalDate desde, LocalDate hasta) {
        return repository.findByUsuarioIdAndFechaBetweenOrderByFechaAsc(usuarioId, desde, hasta)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}

