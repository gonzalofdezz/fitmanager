-- V5__create_usuarios_table.sql
CREATE TABLE usuarios (
    id UUID NOT NULL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    activo BOOLEAN NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL
);

CREATE INDEX idx_usuarios_email ON usuarios(email);

