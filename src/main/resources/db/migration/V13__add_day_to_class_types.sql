-- V13__add_day_to_class_types.sql
-- Agregar el día de la semana a las clases

ALTER TABLE class_types
    ADD COLUMN day_of_week VARCHAR(20) NOT NULL DEFAULT 'MONDAY';

-- Actualizar los datos existentes con días específicos
UPDATE class_types SET day_of_week = 'MONDAY' WHERE name = 'Boxeo';
UPDATE class_types SET day_of_week = 'WEDNESDAY' WHERE name = 'Yoga';
UPDATE class_types SET day_of_week = 'TUESDAY' WHERE name = 'Spinning';
UPDATE class_types SET day_of_week = 'THURSDAY' WHERE name = 'Pilates';
UPDATE class_types SET day_of_week = 'FRIDAY' WHERE name = 'Cross Training';

