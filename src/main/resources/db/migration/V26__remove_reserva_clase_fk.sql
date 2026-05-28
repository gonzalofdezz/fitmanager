-- V26__remove_reserva_clase_fk.sql
-- Eliminar la FK constraint de reservas.clase_id hacia class_types(id)
-- El campo clase_id en reservas representa un número de aula física (1-10),
-- no una referencia a una clase del gimnasio.

ALTER TABLE reservas DROP CONSTRAINT IF EXISTS reservas_clase_id_fkey;

