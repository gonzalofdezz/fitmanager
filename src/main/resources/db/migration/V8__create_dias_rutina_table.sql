-- V8__create_dias_rutina_table.sql
CREATE TABLE dias_rutina (
    id UUID NOT NULL PRIMARY KEY,
    rutina_id UUID NOT NULL,
    dia VARCHAR(50) NOT NULL,
    ejercicios_descripcion TEXT
);

CREATE INDEX idx_dias_rutina_id ON dias_rutina(rutina_id);

