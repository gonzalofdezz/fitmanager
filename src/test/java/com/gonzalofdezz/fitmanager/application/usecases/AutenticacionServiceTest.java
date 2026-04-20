package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.application.ports.input.SuscripcionesInputPort;
import com.gonzalofdezz.fitmanager.application.ports.output.UsuarioRepositoryOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AutenticacionService Tests")
class AutenticacionServiceTest {

    @Mock
    private UsuarioRepositoryOutputPort usuarioRepository;

    @Mock
    private SuscripcionesInputPort suscripcionesInputPort;

    private AutenticacionService autenticacionService;

    @BeforeEach
    void setUp() {
        autenticacionService = new AutenticacionService(usuarioRepository, suscripcionesInputPort);
    }

    @Test
    @DisplayName("Debería registrar un nuevo usuario exitosamente")
    void testRegistrarUsuarioExitoso() {
        // Given
        String nombre = "Juan Pérez";
        String email = "juan@example.com";
        String contrasena = "password123";

        when(usuarioRepository.existsByEmail(email)).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Usuario resultado = autenticacionService.registrar(nombre, email, contrasena);

        // Then
        assertNotNull(resultado);
        assertEquals(nombre, resultado.nombre());
        assertEquals(email, resultado.email());
        assertTrue(resultado.activo());
        assertNotNull(resultado.id());
        assertNotNull(resultado.fechaCreacion());

        verify(usuarioRepository, times(1)).existsByEmail(email);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Debería fallar al registrar con email duplicado")
    void testRegistrarEmailDuplicado() {
        // Given
        String email = "juan@example.com";
        when(usuarioRepository.existsByEmail(email)).thenReturn(true);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            autenticacionService.registrar("Juan", email, "password123");
        });

        verify(usuarioRepository, times(1)).existsByEmail(email);
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debería fallar al registrar con contraseña muy corta")
    void testRegistrarContraseñaMuyCorta() {
        // Given
        String email = "juan@example.com";
        when(usuarioRepository.existsByEmail(email)).thenReturn(false);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            autenticacionService.registrar("Juan", email, "123");
        });

        verify(usuarioRepository, times(1)).existsByEmail(email);
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debería hacer login exitosamente con credenciales válidas")
    void testLoginExitoso() {
        // Given
        String email = "juan@example.com";
        String contrasena = "password123";
        UUID usuarioId = UUID.randomUUID();
        Usuario usuario = new Usuario(
                usuarioId,
                "Juan Pérez",
                email,
                contrasena,
                true,
                LocalDateTime.now()
        );

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // When
        Optional<Usuario> resultado = autenticacionService.login(email, contrasena);

        // Then
        assertTrue(resultado.isPresent());
        assertEquals(usuarioId, resultado.get().id());
        assertEquals(email, resultado.get().email());

        verify(usuarioRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Debería fallar login con contraseña incorrecta")
    void testLoginContraseñaIncorrecta() {
        // Given
        String email = "juan@example.com";
        String contrasena = "password123";
        Usuario usuario = new Usuario(
                UUID.randomUUID(),
                "Juan Pérez",
                email,
                contrasena,
                true,
                LocalDateTime.now()
        );

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // When
        Optional<Usuario> resultado = autenticacionService.login(email, "wrongPassword");

        // Then
        assertTrue(resultado.isEmpty());

        verify(usuarioRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Debería fallar login si usuario no existe")
    void testLoginUsuarioNoExiste() {
        // Given
        String email = "noexiste@example.com";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When
        Optional<Usuario> resultado = autenticacionService.login(email, "password123");

        // Then
        assertTrue(resultado.isEmpty());

        verify(usuarioRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Debería fallar login si usuario está inactivo")
    void testLoginUsuarioInactivo() {
        // Given
        String email = "juan@example.com";
        String contrasena = "password123";
        Usuario usuario = new Usuario(
                UUID.randomUUID(),
                "Juan Pérez",
                email,
                contrasena,
                false, // Usuario inactivo
                LocalDateTime.now()
        );

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // When
        Optional<Usuario> resultado = autenticacionService.login(email, contrasena);

        // Then
        assertTrue(resultado.isEmpty());

        verify(usuarioRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Debería obtener usuario por email")
    void testObtenerPorEmail() {
        // Given
        String email = "juan@example.com";
        UUID usuarioId = UUID.randomUUID();
        Usuario usuario = new Usuario(
                usuarioId,
                "Juan Pérez",
                email,
                "password123",
                true,
                LocalDateTime.now()
        );

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));

        // When
        Optional<Usuario> resultado = autenticacionService.obtenerPorEmail(email);

        // Then
        assertTrue(resultado.isPresent());
        assertEquals(usuarioId, resultado.get().id());
        assertEquals(email, resultado.get().email());

        verify(usuarioRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Debería verificar que email existe")
    void testEmailExiste() {
        // Given
        String email = "juan@example.com";
        when(usuarioRepository.existsByEmail(email)).thenReturn(true);

        // When
        boolean existe = autenticacionService.emailExiste(email);

        // Then
        assertTrue(existe);

        verify(usuarioRepository, times(1)).existsByEmail(email);
    }

    @Test
    @DisplayName("Debería verificar que email no existe")
    void testEmailNoExiste() {
        // Given
        String email = "noexiste@example.com";
        when(usuarioRepository.existsByEmail(email)).thenReturn(false);

        // When
        boolean existe = autenticacionService.emailExiste(email);

        // Then
        assertFalse(existe);

        verify(usuarioRepository, times(1)).existsByEmail(email);
    }
}

