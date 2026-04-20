-- V14__add_fecha_to_class_types.sql
-- Agregar la fecha de la clase a la tabla class_types

ALTER TABLE class_types
    ADD COLUMN fecha DATE NOT NULL DEFAULT CURRENT_DATE;

-- Actualizar los datos existentes con fechas específicas
UPDATE class_types SET fecha = '2026-04-25' WHERE name = 'Boxeo';
UPDATE class_types SET fecha = '2026-04-27' WHERE name = 'Yoga';
UPDATE class_types SET fecha = '2026-04-28' WHERE name = 'Spinning';
UPDATE class_types SET fecha = '2026-04-30' WHERE name = 'Pilates';
UPDATE class_types SET fecha = '2026-05-02' WHERE name = 'Cross Training';

