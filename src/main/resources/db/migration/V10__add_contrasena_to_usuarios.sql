-- V10__add_contrasena_to_usuarios.sql
ALTER TABLE usuarios ADD COLUMN contrasena VARCHAR(255) NOT NULL DEFAULT 'password123';

-- Después de esta migración, todos los usuarios existentes tendrán la contraseña por defecto
-- Considere cambiarla en producción o implementar un reset de contraseña

