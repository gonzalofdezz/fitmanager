package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.LoadClassesOutputPort;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.ClassTypeRepository;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.ClassTypeJpaMapper;
import com.gonzalofdezz.fitmanager.domain.entity.GymClass;
import com.gonzalofdezz.fitmanager.domain.common.ResourceNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClassTypeJpaAdapter implements LoadClassesOutputPort {

    private final ClassTypeRepository classTypeRepository;
    private final ClassTypeJpaMapper classTypeJpaMapper;

    public ClassTypeJpaAdapter(ClassTypeRepository classTypeRepository, ClassTypeJpaMapper classTypeJpaMapper) {
        this.classTypeRepository = classTypeRepository;
        this.classTypeJpaMapper = classTypeJpaMapper;
    }

    @Override
    public List<GymClass> findAll() {
        return classTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(classTypeJpaMapper::toDomain)
                .toList();
    }

    @Override
    public GymClass getClassById(Long id) {
        return classTypeRepository.findById(id)
                .map(classTypeJpaMapper::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException("Clase no encontrada: " + id));
    }

    @Override
    public GymClass save(GymClass gymClass) {
        var jpa = classTypeJpaMapper.toEntity(gymClass);
        var saved = classTypeRepository.save(jpa);
        return classTypeJpaMapper.toDomain(saved);
    }
}
