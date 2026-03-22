package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.SuscripcionJpaEntity;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper.SuscripcionJpaMapper;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.SuscripcionSpringDataRepository;
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
class SuscripcionJpaAdapterOutputTest {

    @Mock
    private SuscripcionSpringDataRepository suscripcionSpringDataRepository;

    @Mock
    private SuscripcionJpaMapper suscripcionJpaMapper;

    @InjectMocks
    private SuscripcionJpaAdapterOutput suscripcionJpaAdapterOutput;

    @Test
    void shouldReturnSuscripcionWhenFoundByUsuarioId() {
        UUID usuarioId = UUID.randomUUID();
        SuscripcionJpaEntity entity = new SuscripcionJpaEntity();
        Suscripcion suscripcion = new Suscripcion(
                UUID.randomUUID(),
                usuarioId,
                "PREMIUM",
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 12, 31, 23, 59, 59),
                true,
                LocalDateTime.of(2026, 1, 1, 10, 0)
        );

        when(suscripcionSpringDataRepository.findByUsuarioId(usuarioId)).thenReturn(Optional.of(entity));
        when(suscripcionJpaMapper.toDomain(entity)).thenReturn(suscripcion);

        Optional<Suscripcion> result = suscripcionJpaAdapterOutput.obtenerPorUsuarioId(usuarioId);

        assertThat(result).contains(suscripcion);
        verify(suscripcionSpringDataRepository).findByUsuarioId(usuarioId);
        verify(suscripcionJpaMapper).toDomain(entity);
    }

    @Test
    void shouldReturnEmptyWhenSuscripcionDoesNotExist() {
        UUID usuarioId = UUID.randomUUID();
        when(suscripcionSpringDataRepository.findByUsuarioId(usuarioId)).thenReturn(Optional.empty());

        Optional<Suscripcion> result = suscripcionJpaAdapterOutput.obtenerPorUsuarioId(usuarioId);

        assertThat(result).isEmpty();
        verify(suscripcionSpringDataRepository).findByUsuarioId(usuarioId);
    }
}
