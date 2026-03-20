package com.gonzalofdezz.fitmanager.gym.infrastructure.mapper;

import com.gonzalofdezz.fitmanager.gym.domain.entity.Gym;
import com.gonzalofdezz.fitmanager.gym.infrastructure.output.jpa.GymJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class GymMapper {

    public GymJpaEntity toJpa(Gym gym) {
        return new GymJpaEntity(
                gym.getId(),
                gym.getName(),
                gym.getPlan(),
                gym.getCreatedAt()
        );
    }

    public Gym toDomain(GymJpaEntity entity) {
        return new Gym(
                entity.getId(),
                entity.getName(),
                entity.getPlan(),
                entity.getCreatedAt()
        );
    }
}