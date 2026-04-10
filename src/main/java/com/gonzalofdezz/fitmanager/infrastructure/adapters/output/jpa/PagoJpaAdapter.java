package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.PagoRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Pago;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.PagoSpringDataRepository;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.PagoJpaMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PagoJpaAdapter implements PagoRepositoryOutputPort {

    private final PagoSpringDataRepository pagoRepository;
    private final PagoJpaMapper pagoMapper;

    public PagoJpaAdapter(PagoSpringDataRepository pagoRepository, PagoJpaMapper pagoMapper) {
        this.pagoRepository = pagoRepository;
        this.pagoMapper = pagoMapper;
    }

    @Override
    public Pago guardar(Pago pago) {
        var jpa = pagoMapper.toJpa(pago);
        var guardado = pagoRepository.save(jpa);
        return pagoMapper.toDomain(guardado);
    }

    @Override
    public Optional<Pago> obtenerPorId(UUID pagoId) {
        return pagoRepository.findById(pagoId).map(pagoMapper::toDomain);
    }

    @Override
    public Optional<Pago> obtenerPorSuscripcionId(UUID suscripcionId) {
        return pagoRepository.findBySuscripcionId(suscripcionId).map(pagoMapper::toDomain);
    }
}

