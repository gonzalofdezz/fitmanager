-- V16__add_rol_to_usuarios.sql
ALTER TABLE usuarios ADD COLUMN rol VARCHAR(20) NOT NULL DEFAULT 'USER';

