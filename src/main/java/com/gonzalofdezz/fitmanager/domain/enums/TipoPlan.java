package com.gonzalofdezz.fitmanager.domain.enums;

public enum TipoPlan {
    NINGUNA("Sin Suscripción"),
    BASICA("Plan Básico"),
    PREMIUM("Plan Premium"),
    VIP("Plan VIP");

    private final String descripcion;

    TipoPlan(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoPlan fromString(String value) {
        try {
            return TipoPlan.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de plan no válido: " + value);
        }
    }
}

