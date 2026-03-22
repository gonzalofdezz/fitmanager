ALTER TABLE class_types
    ADD COLUMN description VARCHAR(255) NOT NULL DEFAULT 'Clase del gimnasio';

ALTER TABLE class_types
    ADD COLUMN level VARCHAR(30) NOT NULL DEFAULT 'ALL_LEVELS';

INSERT INTO gyms (name, plan, created_at)
VALUES ('Fitmanager Central', 'PREMIUM', CURRENT_TIMESTAMP);

INSERT INTO class_types (gym_id, name, duration_min, default_capacity, description, level)
VALUES
    (1, 'Boxeo', 50, 18, 'Entrenamiento de boxeo con trabajo de tecnica, cardio y golpeo.', 'INTERMEDIATE'),
    (1, 'Yoga', 60, 20, 'Sesion enfocada en movilidad, respiracion y control postural.', 'BEGINNER'),
    (1, 'Spinning', 45, 24, 'Clase de ciclismo indoor con bloques de intensidad progresiva.', 'ALL_LEVELS'),
    (1, 'Pilates', 55, 16, 'Trabajo de core, estabilidad y fuerza de bajo impacto.', 'BEGINNER'),
    (1, 'Cross Training', 50, 14, 'Circuito funcional de fuerza y resistencia para grupos reducidos.', 'ADVANCED');
