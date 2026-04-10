package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.EjercicioRutina;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.EjercicioRutinaJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EjercicioRutinaJpaMapper {
    EjercicioRutinaJpaEntity toJpa(EjercicioRutina domain);
    EjercicioRutina toDomain(EjercicioRutinaJpaEntity jpa);
}

