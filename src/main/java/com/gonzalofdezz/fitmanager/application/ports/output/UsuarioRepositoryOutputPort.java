package com.gonzalofdezz.fitmanager.application.ports.output;

import com.gonzalofdezz.fitmanager.domain.entity.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositoryOutputPort {

    /**
     * Guarda un usuario en el repositorio
     * @param usuario el usuario a guardar
     * @return el usuario guardado
     */
    Usuario save(Usuario usuario);

    /**
     * Obtiene un usuario por su email
     * @param email el email del usuario
     * @return el usuario si existe
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Obtiene un usuario por su ID
     * @param id el ID del usuario
     * @return el usuario si existe
     */
    Optional<Usuario> findById(UUID id);

    /**
     * Verifica si un email existe
     * @param email el email a verificar
     * @return true si el email existe, false en caso contrario
     */
    boolean existsByEmail(String email);
}

