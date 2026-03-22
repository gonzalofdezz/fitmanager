package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.ReservaJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ReservaJpaMapper {

    public Reserva toDomain(ReservaJpaEntity entity) {
        return new Reserva(
                entity.getId(),
                entity.getUsuarioId(),
                entity.getClaseId(),
                entity.getFechaReserva(),
                entity.getFechaCreacion()
        );
    }

    public ReservaJpaEntity toEntity(Reserva domain) {
        return new ReservaJpaEntity(
                domain.id(),
                domain.usuarioId(),
                domain.claseId(),
                domain.fechaReserva(),
                domain.fechaCreacion()
        );
    }
}

