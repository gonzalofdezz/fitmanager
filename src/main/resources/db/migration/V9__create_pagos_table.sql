-- V9__create_pagos_table.sql
CREATE TABLE pagos (
    id UUID NOT NULL PRIMARY KEY,
    suscripcion_id UUID NOT NULL,
    monto DOUBLE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    metodo_pago VARCHAR(100) NOT NULL,
    fecha_pago TIMESTAMP,
    referencia_pago VARCHAR(255),
    fecha_creacion TIMESTAMP NOT NULL
);

CREATE INDEX idx_pagos_suscripcion_id ON pagos(suscripcion_id);
CREATE INDEX idx_pagos_estado ON pagos(estado);

