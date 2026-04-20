package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.domain.entity.GymClass;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.ClassTypeJpaEntity;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.ClassTypeJpaMapper;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.ClassTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClassTypeJpaAdapterTest {

    @Mock
    private ClassTypeRepository classTypeRepository;

    @Mock
    private ClassTypeJpaMapper classTypeJpaMapper;

    @InjectMocks
    private ClassTypeJpaAdapter classTypeJpaAdapter;

    @Test
    void shouldLoadAllClassesSortedById() {
        ClassTypeJpaEntity entity = new ClassTypeJpaEntity();
        GymClass gymClass = new GymClass(1L, "Yoga", "Clase", "BEGINNER", 60, 20, 1L);

        when(classTypeRepository.findAll(any(Sort.class))).thenReturn(List.of(entity));
        when(classTypeJpaMapper.toDomain(entity)).thenReturn(gymClass);

        List<GymClass> result = classTypeJpaAdapter.findAll();

        assertThat(result).containsExactly(gymClass);
        verify(classTypeRepository).findAll(Sort.by(Sort.Direction.ASC, "id"));
        verify(classTypeJpaMapper).toDomain(entity);
    }
}
