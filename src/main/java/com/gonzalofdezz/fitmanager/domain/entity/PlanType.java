package com.gonzalofdezz.fitmanager.domain.entity;

public enum PlanType {
    BASIC(30, 9.99),      // 30 días - €9.99
    PREMIUM(60, 19.99),   // 60 días - €19.99
    VIP(90, 29.99);       // 90 días - €29.99
    
    private final int dias;
    private final double precio;
    
    PlanType(int dias, double precio) {
        this.dias = dias;
        this.precio = precio;
    }
    
    public int getDias() {
        return dias;
    }
    
    public double getPrecio() {
        return precio;
    }
}

