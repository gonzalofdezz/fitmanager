package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.MedicionProgresion;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.MedicionProgresionJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class MedicionProgresionJpaMapper {
    public MedicionProgresion toDomain(MedicionProgresionJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new MedicionProgresion(
                entity.getId(),
                entity.getUsuarioId(),
                entity.getPeso(),
                entity.getPesoMaximoLevantado(),
                entity.getFecha(),
                entity.getNotas(),
                entity.getFechaCreacion(),
                entity.getFechaActualizacion()
        );
    }

    public MedicionProgresionJpaEntity toEntity(MedicionProgresion domain) {
        if (domain == null) {
            return null;
        }
        return MedicionProgresionJpaEntity.builder()
                .id(domain.id())
                .usuarioId(domain.usuarioId())
                .peso(domain.peso())
                .pesoMaximoLevantado(domain.pesoMaximoLevantado())
                .fecha(domain.fecha())
                .notas(domain.notas())
                .fechaCreacion(domain.fechaCreacion())
                .fechaActualizacion(domain.fechaActualizacion())
                .build();
    }
}

