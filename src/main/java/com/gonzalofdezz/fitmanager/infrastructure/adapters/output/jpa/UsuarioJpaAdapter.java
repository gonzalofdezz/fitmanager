package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.application.ports.output.UsuarioRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Usuario;
import com.gonzalofdezz.fitmanager.domain.enums.RolUsuario;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.UsuarioJpaEntity;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.UsuarioSpringDataRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UsuarioJpaAdapter implements UsuarioRepositoryOutputPort {

    private final UsuarioSpringDataRepository usuarioRepository;

    public UsuarioJpaAdapter(UsuarioSpringDataRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        var entity = new UsuarioJpaEntity(
                usuario.id(),
                usuario.nombre(),
                usuario.email(),
                usuario.contrasena(),
                usuario.activo(),
                usuario.fechaCreacion(),
                usuario.rol() != null ? usuario.rol().name() : RolUsuario.USER.name()
        );
        var saved = usuarioRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public Optional<Usuario> findById(UUID id) {
        return usuarioRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    private Usuario toDomain(UsuarioJpaEntity entity) {
        RolUsuario rol;
        try {
            rol = RolUsuario.valueOf(entity.getRol());
        } catch (Exception e) {
            rol = RolUsuario.USER;
        }
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getEmail(),
                entity.getContrasena(),
                entity.getActivo(),
                entity.getFechaCreacion(),
                rol
        );
    }
}

