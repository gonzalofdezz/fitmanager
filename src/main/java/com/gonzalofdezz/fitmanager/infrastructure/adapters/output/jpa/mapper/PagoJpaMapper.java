package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.Pago;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.PagoJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagoJpaMapper {
    PagoJpaEntity toJpa(Pago domain);
    Pago toDomain(PagoJpaEntity jpa);
}

