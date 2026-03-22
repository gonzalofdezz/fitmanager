package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.ReservaJpaEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ReservaJpaMapperTest {

    private final ReservaJpaMapper mapper = new ReservaJpaMapper();

    @Test
    void shouldMapEntityToDomain() {
        UUID id = UUID.randomUUID();
        UUID usuarioId = UUID.randomUUID();
        LocalDateTime fechaReserva = LocalDateTime.of(2026, 3, 25, 10, 30);
        LocalDateTime fechaCreacion = LocalDateTime.of(2026, 3, 22, 13, 16, 43);

        ReservaJpaEntity entity = new ReservaJpaEntity(id, usuarioId, 1L, fechaReserva, fechaCreacion);

        Reserva result = mapper.toDomain(entity);

        assertThat(result.id()).isEqualTo(id);
        assertThat(result.usuarioId()).isEqualTo(usuarioId);
        assertThat(result.claseId()).isEqualTo(1L);
        assertThat(result.fechaReserva()).isEqualTo(fechaReserva);
        assertThat(result.fechaCreacion()).isEqualTo(fechaCreacion);
    }

    @Test
    void shouldMapDomainToEntity() {
        UUID id = UUID.randomUUID();
        UUID usuarioId = UUID.randomUUID();
        LocalDateTime fechaReserva = LocalDateTime.of(2026, 3, 25, 10, 30);
        LocalDateTime fechaCreacion = LocalDateTime.of(2026, 3, 22, 13, 16, 43);

        Reserva reserva = new Reserva(id, usuarioId, 1L, fechaReserva, fechaCreacion);

        ReservaJpaEntity result = mapper.toEntity(reserva);

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUsuarioId()).isEqualTo(usuarioId);
        assertThat(result.getClaseId()).isEqualTo(1L);
        assertThat(result.getFechaReserva()).isEqualTo(fechaReserva);
        assertThat(result.getFechaCreacion()).isEqualTo(fechaCreacion);
    }
}
