-- V17__create_progresion_table.sql
CREATE TABLE progresion (
    id UUID NOT NULL PRIMARY KEY,
    usuario_id UUID NOT NULL,
    peso_corporal DECIMAL(5, 2),
    entrenamientos_completados INTEGER NOT NULL DEFAULT 0,
    racha_asistencias INTEGER NOT NULL DEFAULT 0,
    ultima_actividad TIMESTAMP,
    bandejas_conseguidas TEXT,
    peso_maximo_levantado DECIMAL(8, 2),
    fecha_creacion TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP NOT NULL
);

CREATE INDEX idx_progresion_usuario_id ON progresion(usuario_id);
CREATE UNIQUE INDEX idx_progresion_usuario_unique ON progresion(usuario_id);

