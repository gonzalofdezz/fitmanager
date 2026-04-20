package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.GymClass;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.ClassTypeJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ClassTypeJpaMapper {

    public GymClass toDomain(ClassTypeJpaEntity entity) {
        return new GymClass(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getLevel(),
                entity.getDurationMinutes(),
                entity.getDefaultCapacity(),
                entity.getDayOfWeek(),
                entity.getFecha(),
                entity.getGymId()
        );
    }

    public ClassTypeJpaEntity toEntity(GymClass domain) {
        ClassTypeJpaEntity entity = new ClassTypeJpaEntity();
        if (domain.id() != null) {
            entity.setId(domain.id());
        }
        entity.setName(domain.nombre());
        entity.setDescription(domain.descripcion());
        entity.setLevel(domain.nivel());
        entity.setDurationMinutes(domain.duracionMinutos());
        entity.setDefaultCapacity(domain.capacidadPorDefecto());
        entity.setDayOfWeek(domain.diaSemana());
        entity.setFecha(domain.fecha());
        entity.setGymId(domain.gymId());
        return entity;
    }
}

