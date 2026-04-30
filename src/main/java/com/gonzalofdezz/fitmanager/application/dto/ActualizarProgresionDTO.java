package com.gonzalofdezz.fitmanager.application.dto;

public record ActualizarProgresionDTO(
        Double pesoCorporal,
        Integer entrenamientosCompletados,
        Integer rachaAsistencias,
        String banderasConseguidas,
        Double pesoMaximoLevantado
) {
}

