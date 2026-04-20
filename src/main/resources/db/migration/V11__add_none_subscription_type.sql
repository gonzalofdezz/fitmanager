-- V11__add_none_subscription_type.sql
-- Agregar documentación sobre el nuevo tipo de suscripción NINGUNA
-- Los usuarios nuevos tendrán una suscripción con tipo NINGUNA hasta que compren una plan

-- Este cambio es principalmente a nivel de aplicación
-- Se agrega el tipo NINGUNA al enum TipoPlan en el dominio
-- que representa un usuario sin suscripción activa

