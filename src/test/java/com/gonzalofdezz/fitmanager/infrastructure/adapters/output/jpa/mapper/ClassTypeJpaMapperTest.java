package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.GymClass;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.ClassTypeJpaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ClassTypeJpaMapperTest {

    private final ClassTypeJpaMapper mapper = new ClassTypeJpaMapper();

    @Test
    void shouldMapEntityToDomain() {
        ClassTypeJpaEntity entity = new ClassTypeJpaEntity();
        ReflectionTestUtils.setField(entity, "id", 1L);
        ReflectionTestUtils.setField(entity, "name", "Yoga");
        ReflectionTestUtils.setField(entity, "description", "Clase suave");
        ReflectionTestUtils.setField(entity, "level", "BEGINNER");
        ReflectionTestUtils.setField(entity, "durationMinutes", 60);
        ReflectionTestUtils.setField(entity, "defaultCapacity", 20);

        GymClass result = mapper.toDomain(entity);

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.nombre()).isEqualTo("Yoga");
        assertThat(result.descripcion()).isEqualTo("Clase suave");
        assertThat(result.nivel()).isEqualTo("BEGINNER");
        assertThat(result.duracionMinutos()).isEqualTo(60);
        assertThat(result.capacidadPorDefecto()).isEqualTo(20);
    }

    @Test
    void shouldThrowWhenMappingDomainToEntity() {
        GymClass gymClass = new GymClass(1L, "Yoga", "Clase suave", "BEGINNER", 60, 20);

        assertThatThrownBy(() -> mapper.toEntity(gymClass))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Entity creation not yet implemented");
    }
}
