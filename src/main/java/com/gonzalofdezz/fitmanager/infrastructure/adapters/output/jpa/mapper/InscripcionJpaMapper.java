package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.Inscripcion;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.InscripcionJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class InscripcionJpaMapper {

    public Inscripcion toDomain(InscripcionJpaEntity entity) {
        return new Inscripcion(
                entity.getId(),
                entity.getUsuarioId(),
                entity.getClaseId(),
                entity.getFechaInscripcion()
        );
    }

    public InscripcionJpaEntity toEntity(Inscripcion domain) {
        InscripcionJpaEntity entity = new InscripcionJpaEntity();
        entity.setId(domain.id());
        entity.setUsuarioId(domain.usuarioId());
        entity.setClaseId(domain.claseId());
        entity.setFechaInscripcion(domain.fechaInscripcion());
        return entity;
    }
}

