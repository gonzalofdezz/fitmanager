-- V7__create_ejercicios_rutina_table.sql
CREATE TABLE ejercicios_rutina (
    id UUID NOT NULL PRIMARY KEY,
    rutina_id UUID NOT NULL,
    nombre_ejercicio VARCHAR(255) NOT NULL,
    series INTEGER NOT NULL,
    repeticiones INTEGER NOT NULL,
    peso DOUBLE PRECISION,
    descanso_segundos INTEGER
);

CREATE INDEX idx_ejercicios_rutina_id ON ejercicios_rutina(rutina_id);

