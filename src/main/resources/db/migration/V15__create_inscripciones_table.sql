-- V15__create_inscripciones_table.sql
-- Tabla para inscripciones a clases del gimnasio

CREATE TABLE inscripciones (
    id UUID PRIMARY KEY,
    usuario_id UUID NOT NULL,
    clase_id BIGINT NOT NULL,
    fecha_inscripcion TIMESTAMP NOT NULL,
    CONSTRAINT fk_inscripciones_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_inscripciones_clase
        FOREIGN KEY (clase_id) REFERENCES class_types(id)
        ON DELETE CASCADE,
    CONSTRAINT uk_inscripciones_usuario_clase UNIQUE (usuario_id, clase_id)
);

CREATE INDEX idx_inscripciones_usuario_id ON inscripciones(usuario_id);

