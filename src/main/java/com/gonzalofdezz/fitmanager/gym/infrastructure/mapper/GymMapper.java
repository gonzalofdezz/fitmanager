package com.gonzalofdezz.fitmanager.gym.infrastructure.mapper;

import com.gonzalofdezz.fitmanager.gym.domain.entity.Gym;
import com.gonzalofdezz.fitmanager.gym.infrastructure.output.jpa.GymJpaEntity;

public class GymMapper {

    public GymJpaEntity toJpa(Gym gym) {
        return new GymJpaEntity(
                gym.getId(),
                gym.getName(),
                gym.getPlan().name(),
                gym.getCreatedAt()
        );
    }

    public Gym toDomain(GymJpaEntity entity) {
        return new Gym(
                entity.getId(),
                entity.getName(),
                entity.getPlanEnum(),
                entity.getCreatedAt()
        );
    }
}
