package com.gonzalofdezz.fitmanager.config;

import com.gonzalofdezz.fitmanager.application.ports.output.UsuarioRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Usuario;
import com.gonzalofdezz.fitmanager.domain.enums.RolUsuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepositoryOutputPort usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepositoryOutputPort usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Crear cuenta de administrador/manager si no existe
        if (!usuarioRepository.existsByEmail("manager@fitmanager.com")) {
            var manager = new Usuario(
                    UUID.randomUUID(),
                    "Manager",
                    "manager@fitmanager.com",
                    passwordEncoder.encode("manager123"),
                    true,
                    LocalDateTime.now(),
                    RolUsuario.MANAGER
            );
            usuarioRepository.save(manager);
            System.out.println("✓ Cuenta manager creada: manager@fitmanager.com / manager123");
        }
    }
}

