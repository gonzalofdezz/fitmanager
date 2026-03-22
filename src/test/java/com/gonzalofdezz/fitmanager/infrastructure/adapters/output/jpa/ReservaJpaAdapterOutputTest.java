package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.ReservaJpaEntity;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.ReservaJpaMapper;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.ReservaSpringDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservaJpaAdapterOutputTest {

    @Mock
    private ReservaSpringDataRepository reservaSpringDataRepository;

    @Mock
    private ReservaJpaMapper reservaJpaMapper;

    @InjectMocks
    private ReservaJpaAdapterOutput reservaJpaAdapterOutput;

    @Test
    void shouldSaveReserva() {
        UUID id = UUID.randomUUID();
        Reserva reserva = new Reserva(id, UUID.randomUUID(), 1L,
                LocalDateTime.of(2026, 3, 25, 10, 30),
                LocalDateTime.of(2026, 3, 22, 13, 16, 43));
        ReservaJpaEntity entity = new ReservaJpaEntity();

        when(reservaJpaMapper.toEntity(reserva)).thenReturn(entity);
        when(reservaSpringDataRepository.save(entity)).thenReturn(entity);
        when(reservaJpaMapper.toDomain(entity)).thenReturn(reserva);

        Reserva result = reservaJpaAdapterOutput.guardar(reserva);

        assertThat(result).isEqualTo(reserva);
        verify(reservaJpaMapper).toEntity(reserva);
        verify(reservaSpringDataRepository).save(entity);
        verify(reservaJpaMapper).toDomain(entity);
    }

    @Test
    void shouldReturnReservaByIdWhenPresent() {
        UUID id = UUID.randomUUID();
        ReservaJpaEntity entity = new ReservaJpaEntity();
        Reserva reserva = new Reserva(id, UUID.randomUUID(), 1L,
                LocalDateTime.of(2026, 3, 25, 10, 30),
                LocalDateTime.of(2026, 3, 22, 13, 16, 43));

        when(reservaSpringDataRepository.findById(id)).thenReturn(Optional.of(entity));
        when(reservaJpaMapper.toDomain(entity)).thenReturn(reserva);

        Optional<Reserva> result = reservaJpaAdapterOutput.obtenerPorId(id);

        assertThat(result).contains(reserva);
        verify(reservaSpringDataRepository).findById(id);
        verify(reservaJpaMapper).toDomain(entity);
    }

    @Test
    void shouldReturnEmptyWhenReservaByIdDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(reservaSpringDataRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Reserva> result = reservaJpaAdapterOutput.obtenerPorId(id);

        assertThat(result).isEmpty();
        verify(reservaSpringDataRepository).findById(id);
    }
}
