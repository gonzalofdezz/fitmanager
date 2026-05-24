-- V12__make_suscripcion_dates_nullable.sql
-- Hacer que fecha_inicio y fecha_fin sean opcionales en la tabla suscripciones
-- Esto permite que la suscripción NINGUNA (sin plan) no tenga fechas definidas

ALTER TABLE suscripciones
    ALTER COLUMN fecha_inicio DROP NOT NULL;

ALTER TABLE suscripciones
    ALTER COLUMN fecha_fin DROP NOT NULL;

