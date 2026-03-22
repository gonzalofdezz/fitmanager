package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.SuscripcionJpaEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class SuscripcionJpaMapperTest {

    private final SuscripcionJpaMapper mapper = new SuscripcionJpaMapper();

    @Test
    void shouldMapEntityToDomain() {
        UUID id = UUID.randomUUID();
        UUID usuarioId = UUID.randomUUID();
        LocalDateTime fechaInicio = LocalDateTime.of(2026, 1, 1, 0, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2026, 12, 31, 23, 59, 59);
        LocalDateTime fechaCreacion = LocalDateTime.of(2026, 1, 1, 10, 0);

        SuscripcionJpaEntity entity = new SuscripcionJpaEntity(
                id, usuarioId, "PREMIUM", fechaInicio, fechaFin, true, fechaCreacion
        );

        Suscripcion result = mapper.toDomain(entity);

        assertThat(result.id()).isEqualTo(id);
        assertThat(result.usuarioId()).isEqualTo(usuarioId);
        assertThat(result.tipoPlan()).isEqualTo("PREMIUM");
        assertThat(result.fechaInicio()).isEqualTo(fechaInicio);
        assertThat(result.fechaFin()).isEqualTo(fechaFin);
        assertThat(result.activa()).isTrue();
        assertThat(result.fechaCreacion()).isEqualTo(fechaCreacion);
    }

    @Test
    void shouldMapDomainToEntity() {
        UUID id = UUID.randomUUID();
        UUID usuarioId = UUID.randomUUID();
        LocalDateTime fechaInicio = LocalDateTime.of(2026, 1, 1, 0, 0);
        LocalDateTime fechaFin = LocalDateTime.of(2026, 12, 31, 23, 59, 59);
        LocalDateTime fechaCreacion = LocalDateTime.of(2026, 1, 1, 10, 0);

        Suscripcion suscripcion = new Suscripcion(
                id, usuarioId, "PREMIUM", fechaInicio, fechaFin, true, fechaCreacion
        );

        SuscripcionJpaEntity result = mapper.toEntity(suscripcion);

        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getUsuarioId()).isEqualTo(usuarioId);
        assertThat(result.getTipoPlan()).isEqualTo("PREMIUM");
        assertThat(result.getFechaInicio()).isEqualTo(fechaInicio);
        assertThat(result.getFechaFin()).isEqualTo(fechaFin);
        assertThat(result.getActiva()).isTrue();
        assertThat(result.getFechaCreacion()).isEqualTo(fechaCreacion);
    }
}
