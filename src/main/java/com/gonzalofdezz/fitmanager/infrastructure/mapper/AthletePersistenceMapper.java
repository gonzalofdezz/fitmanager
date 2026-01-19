package com.gonzalofdezz.fitmanager.infrastructure.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.Athlete;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.AthleteJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AthletePersistenceMapper {
    AthleteJpaEntity toJpa(Athlete domain);
    Athlete toDomain(AthleteJpaEntity jpa);
}
