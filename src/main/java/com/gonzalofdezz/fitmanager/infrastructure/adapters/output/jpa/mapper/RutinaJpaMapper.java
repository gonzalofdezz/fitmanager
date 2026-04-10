package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.Rutina;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.RutinaJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RutinaJpaMapper {
    RutinaJpaEntity toJpa(Rutina domain);
    Rutina toDomain(RutinaJpaEntity jpa);
}

