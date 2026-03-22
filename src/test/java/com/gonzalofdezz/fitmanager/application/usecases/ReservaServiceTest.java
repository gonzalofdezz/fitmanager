package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.ports.output.ReservaRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepositoryOutputPort reservaRepositoryOutputPort;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void shouldCreateReservaAndPersistIt() {
        UUID usuarioId = UUID.randomUUID();
        LocalDateTime fechaReserva = LocalDateTime.of(2026, 3, 25, 10, 30);

        when(reservaRepositoryOutputPort.guardar(any(Reserva.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Reserva result = reservaService.crear(usuarioId, 1L, fechaReserva);

        ArgumentCaptor<Reserva> captor = ArgumentCaptor.forClass(Reserva.class);
        verify(reservaRepositoryOutputPort).guardar(captor.capture());

        Reserva persisted = captor.getValue();
        assertThat(persisted.id()).isNotNull();
        assertThat(persisted.usuarioId()).isEqualTo(usuarioId);
        assertThat(persisted.claseId()).isEqualTo(1L);
        assertThat(persisted.fechaReserva()).isEqualTo(fechaReserva);
        assertThat(persisted.fechaCreacion()).isNotNull();

        assertThat(result).isEqualTo(persisted);
    }

    @Test
    void shouldDelegateCrearReservaToCrear() {
        UUID usuarioId = UUID.randomUUID();
        LocalDateTime fechaReserva = LocalDateTime.of(2026, 3, 25, 10, 30);

        when(reservaRepositoryOutputPort.guardar(any(Reserva.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Reserva result = reservaService.crearReserva(usuarioId, 2L, fechaReserva);

        assertThat(result.usuarioId()).isEqualTo(usuarioId);
        assertThat(result.claseId()).isEqualTo(2L);
        assertThat(result.fechaReserva()).isEqualTo(fechaReserva);
        verify(reservaRepositoryOutputPort).guardar(any(Reserva.class));
    }
}
