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
                entity.getDefaultCapacity()
        );
    }

    public ClassTypeJpaEntity toEntity(GymClass domain) {
        // This is a simple conversion - if you need full entity conversion,
        // you'll need to implement entity creation/update logic
        throw new UnsupportedOperationException("Entity creation not yet implemented");
    }
}

