package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.MedicionProgresion;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MedicionProgresionRepositoryOutputPort {
    MedicionProgresion guardar(MedicionProgresion medicion);
    List<MedicionProgresion> obtenerPorUsuarioId(UUID usuarioId);
    List<MedicionProgresion> obtenerPorUsuarioYFechas(UUID usuarioId, LocalDate desde, LocalDate hasta);
}

