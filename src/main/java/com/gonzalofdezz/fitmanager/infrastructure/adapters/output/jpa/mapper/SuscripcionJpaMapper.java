package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.SuscripcionJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class SuscripcionJpaMapper {

    public Suscripcion toDomain(SuscripcionJpaEntity entity) {
        return new Suscripcion(
                entity.getId(),
                entity.getUsuarioId(),
                entity.getTipoPlan(),
                entity.getFechaInicio(),
                entity.getFechaFin(),
                entity.getActiva(),
                entity.getFechaCreacion()
        );
    }

    public SuscripcionJpaEntity toEntity(Suscripcion domain) {
        return new SuscripcionJpaEntity(
                domain.id(),
                domain.usuarioId(),
                domain.tipoPlan(),
                domain.fechaInicio(),
                domain.fechaFin(),
                domain.activa(),
                domain.fechaCreacion()
        );
    }
}

