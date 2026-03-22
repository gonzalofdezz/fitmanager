package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.ports.output.SuscripcionRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
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
class SuscripcionServiceTest {

    @Mock
    private SuscripcionRepositoryOutputPort suscripcionRepositoryOutputPort;

    @InjectMocks
    private SuscripcionService suscripcionService;

    @Test
    void shouldReturnSuscripcionFromRepository() {
        UUID usuarioId = UUID.randomUUID();
        Optional<Suscripcion> expected = Optional.of(new Suscripcion(
                UUID.randomUUID(),
                usuarioId,
                "PREMIUM",
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 12, 31, 23, 59, 59),
                true,
                LocalDateTime.of(2026, 1, 1, 10, 0)
        ));
        when(suscripcionRepositoryOutputPort.obtenerPorUsuarioId(usuarioId)).thenReturn(expected);

        Optional<Suscripcion> result = suscripcionService.obtenerPorUsuarioId(usuarioId);

        assertThat(result).isSameAs(expected);
        verify(suscripcionRepositoryOutputPort).obtenerPorUsuarioId(usuarioId);
    }

    @Test
    void shouldDelegateObtenerSuscripcion() {
        UUID usuarioId = UUID.randomUUID();
        Optional<Suscripcion> expected = Optional.empty();
        when(suscripcionRepositoryOutputPort.obtenerPorUsuarioId(usuarioId)).thenReturn(expected);

        Optional<Suscripcion> result = suscripcionService.obtenerSuscripcion(usuarioId);

        assertThat(result).isSameAs(expected);
        verify(suscripcionRepositoryOutputPort).obtenerPorUsuarioId(usuarioId);
    }
}
