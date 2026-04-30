package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.Progresion;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.ProgresionJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProgresionJpaMapper {
    ProgresionJpaEntity toJpa(Progresion domain);
    Progresion toDomain(ProgresionJpaEntity jpa);
}

