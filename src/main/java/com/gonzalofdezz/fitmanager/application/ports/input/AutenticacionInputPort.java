package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.domain.entity.Usuario;

import java.util.Optional;

public interface AutenticacionInputPort {

    /**
     * Registra un nuevo usuario
     * @param nombre nombre del usuario
     * @param email email del usuario
     * @param contrasena contraseña del usuario
     * @return el usuario registrado
     */
    Usuario registrar(String nombre, String email, String contrasena);

    /**
     * Realiza login del usuario
     * @param email email del usuario
     * @param contrasena contraseña del usuario
     * @return el usuario si las credenciales son válidas
     */
    Optional<Usuario> login(String email, String contrasena);

    /**
     * Obtiene un usuario por su email
     * @param email email del usuario
     * @return el usuario si existe
     */
    Optional<Usuario> obtenerPorEmail(String email);

    /**
     * Verifica si un email ya existe
     * @param email email a verificar
     * @return true si el email existe, false en caso contrario
     */
    boolean emailExiste(String email);
}

