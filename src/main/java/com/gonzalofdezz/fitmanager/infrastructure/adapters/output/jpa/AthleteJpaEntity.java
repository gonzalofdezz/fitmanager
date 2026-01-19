package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "athletes")
@Getter
@Setter
@NoArgsConstructor
public class AthleteJpaEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer age;
}
