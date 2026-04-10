package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.DiaRutina;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.DiaRutinaJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DiaRutinaJpaMapper {
    DiaRutinaJpaEntity toJpa(DiaRutina domain);
    DiaRutina toDomain(DiaRutinaJpaEntity jpa);
}

