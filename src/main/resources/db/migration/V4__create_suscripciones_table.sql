CREATE TABLE suscripciones (
    id UUID NOT NULL PRIMARY KEY,
    usuario_id UUID NOT NULL UNIQUE,
    tipo_plan VARCHAR(50) NOT NULL,
    fecha_inicio TIMESTAMP NOT NULL,
    fecha_fin TIMESTAMP NOT NULL,
    activa BOOLEAN NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL
);

CREATE INDEX idx_suscripciones_usuario_id ON suscripciones(usuario_id);

