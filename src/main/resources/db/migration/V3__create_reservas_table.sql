CREATE TABLE reservas (
    id UUID NOT NULL PRIMARY KEY,
    usuario_id UUID NOT NULL,
    clase_id BIGINT NOT NULL,
    fecha_reserva TIMESTAMP NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    FOREIGN KEY (clase_id) REFERENCES class_types(id)
);

