package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.RutinaRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Rutina;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.RutinaSpringDataRepository;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.RutinaJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RutinaJpaAdapter implements RutinaRepositoryOutputPort {

    private final RutinaSpringDataRepository rutinaRepository;
    private final RutinaJpaMapper rutinaMapper;

    public RutinaJpaAdapter(RutinaSpringDataRepository rutinaRepository, RutinaJpaMapper rutinaMapper) {
        this.rutinaRepository = rutinaRepository;
        this.rutinaMapper = rutinaMapper;
    }

    @Override
    public Rutina guardar(Rutina rutina) {
        var jpa = rutinaMapper.toJpa(rutina);
        var guardada = rutinaRepository.save(jpa);
        return rutinaMapper.toDomain(guardada);
    }

    @Override
    public Optional<Rutina> obtenerPorId(UUID id) {
        return rutinaRepository.findById(id).map(rutinaMapper::toDomain);
    }

    @Override
    public List<Rutina> listarPorUsuario(UUID usuarioId) {
        return rutinaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(rutinaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(UUID id) {
        rutinaRepository.deleteById(id);
    }

    @Override
    public List<Rutina> obtenerTodas() {
        return rutinaRepository.findAll()
                .stream()
                .map(rutinaMapper::toDomain)
                .collect(Collectors.toList());
    }
}

