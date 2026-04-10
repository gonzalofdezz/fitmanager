-- V6__create_rutinas_table.sql
CREATE TABLE rutinas (
    id UUID NOT NULL PRIMARY KEY,
    usuario_id UUID NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    activa BOOLEAN NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL
);

CREATE INDEX idx_rutinas_usuario_id ON rutinas(usuario_id);

