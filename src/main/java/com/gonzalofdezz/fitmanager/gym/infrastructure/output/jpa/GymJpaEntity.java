package com.gonzalofdezz.fitmanager.gym.infrastructure.output.jpa;

import com.gonzalofdezz.fitmanager.gym.domain.enums.GymPlan;
import jakarta.persistence.*;

        import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "gyms")
public class GymJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 20)
    private String plan;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected GymJpaEntity() {}

    public GymJpaEntity(UUID id, String name, String plan, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.plan = plan;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getPlan() { return plan; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public GymPlan getPlanEnum() {
        return GymPlan.valueOf(plan);
    }
}
