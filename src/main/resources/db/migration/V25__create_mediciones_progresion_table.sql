CREATE TABLE mediciones_progresion (
    id UUID NOT NULL PRIMARY KEY,
    usuario_id UUID NOT NULL,
    peso DOUBLE PRECISION,
    peso_maximo_levantado DOUBLE PRECISION,
    fecha DATE NOT NULL,
    notas TEXT,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE INDEX idx_usuario_id ON mediciones_progresion(usuario_id);
CREATE INDEX idx_fecha ON mediciones_progresion(fecha);

