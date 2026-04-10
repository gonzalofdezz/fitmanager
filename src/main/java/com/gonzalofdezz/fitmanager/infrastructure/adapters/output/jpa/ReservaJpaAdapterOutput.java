package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.ReservaRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.ReservaSpringDataRepository;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.ReservaJpaMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ReservaJpaAdapterOutput implements ReservaRepositoryOutputPort {

    private final ReservaSpringDataRepository reservaSpringDataRepository;
    private final ReservaJpaMapper reservaJpaMapper;

    public ReservaJpaAdapterOutput(ReservaSpringDataRepository reservaSpringDataRepository, ReservaJpaMapper reservaJpaMapper) {
        this.reservaSpringDataRepository = reservaSpringDataRepository;
        this.reservaJpaMapper = reservaJpaMapper;
    }

    @Override
    public Reserva guardar(Reserva reserva) {
        var savedEntity = reservaSpringDataRepository.save(reservaJpaMapper.toEntity(reserva));
        return reservaJpaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Reserva> obtenerPorId(UUID id) {
        return reservaSpringDataRepository.findById(id).map(reservaJpaMapper::toDomain);
    }

    @Override
    public List<Reserva> listarPorUsuario(UUID usuarioId) {
        return reservaSpringDataRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(reservaJpaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reserva> obtenerPorIdConEstado(UUID id, String estado) {
        return obtenerPorId(id);
    }

    @Override
    public void eliminar(UUID id) {
        reservaSpringDataRepository.deleteById(id);
    }

    @Override
    public List<Reserva> listarPorClaseId(Long claseId) {
        return reservaSpringDataRepository.findByClaseId(claseId)
                .stream()
                .map(reservaJpaMapper::toDomain)
                .collect(Collectors.toList());
    }
}

