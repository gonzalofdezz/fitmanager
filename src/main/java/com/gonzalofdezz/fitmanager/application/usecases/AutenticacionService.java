package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.ports.input.AutenticacionInputPort;
import com.gonzalofdezz.fitmanager.application.ports.input.SuscripcionesInputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.UsuarioRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Usuario;
import com.gonzalofdezz.fitmanager.domain.enums.RolUsuario;
import com.gonzalofdezz.fitmanager.domain.enums.TipoPlan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutenticacionService implements AutenticacionInputPort {

    private final UsuarioRepositoryOutputPort usuarioRepository;
    private final SuscripcionesInputPort suscripcionesInputPort;
    private final PasswordEncoder passwordEncoder;

    public AutenticacionService(UsuarioRepositoryOutputPort usuarioRepository,
                                SuscripcionesInputPort suscripcionesInputPort,
                                PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.suscripcionesInputPort = suscripcionesInputPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario registrar(String nombre, String email, String contrasena) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        if (contrasena.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        var usuario = new Usuario(
                UUID.randomUUID(),
                nombre,
                email,
                passwordEncoder.encode(contrasena),
                true,
                LocalDateTime.now(),
                RolUsuario.USER
        );

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Crear suscripción NINGUNA por defecto
        suscripcionesInputPort.crearSuscripcion(
                usuarioGuardado.id(),
                TipoPlan.NINGUNA.name(),
                null,
                null
        );

        return usuarioGuardado;
    }

    @Override
    public Optional<Usuario> login(String email, String contrasena) {
        return usuarioRepository.findByEmail(email)
                .filter(usuario -> usuario.activo() && passwordEncoder.matches(contrasena, usuario.contrasena()));
    }

    @Override
    public Optional<Usuario> obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public boolean emailExiste(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}

