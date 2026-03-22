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

INSERT INTO suscripciones (
    id,
    usuario_id,
    tipo_plan,
    fecha_inicio,
    fecha_fin,
    activa,
    fecha_creacion
) VALUES (
    '550e8400-e29b-41d4-a716-446655440002',
    '550e8400-e29b-41d4-a716-446655440000',
    'PREMIUM',
    TIMESTAMP '2026-01-01 00:00:00',
    TIMESTAMP '2026-12-31 23:59:59',
    TRUE,
    TIMESTAMP '2026-01-01 10:00:00'
);

