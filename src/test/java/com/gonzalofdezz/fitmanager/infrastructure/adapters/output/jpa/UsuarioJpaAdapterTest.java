package com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa;

import com.gonzalofdezz.fitmanager.domain.entity.Usuario;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.data.UsuarioJpaEntity;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.output.jpa.repository.UsuarioSpringDataRepository;
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
@DisplayName("UsuarioJpaAdapter Tests")
class UsuarioJpaAdapterTest {

    @Mock
    private UsuarioSpringDataRepository usuarioRepository;

    private UsuarioJpaAdapter usuarioJpaAdapter;

    @BeforeEach
    void setUp() {
        usuarioJpaAdapter = new UsuarioJpaAdapter(usuarioRepository);
    }

    @Test
    @DisplayName("Debería guardar un usuario exitosamente")
    void testSaveUsuario() {
        // Given
        UUID usuarioId = UUID.randomUUID();
        Usuario usuario = new Usuario(
                usuarioId,
                "Juan Pérez",
                "juan@example.com",
                "password123",
                true,
                LocalDateTime.now()
        );

        UsuarioJpaEntity entity = new UsuarioJpaEntity(
                usuarioId,
                "Juan Pérez",
                "juan@example.com",
                "password123",
                true,
                usuario.fechaCreacion()
        );

        when(usuarioRepository.save(any(UsuarioJpaEntity.class))).thenReturn(entity);

        // When
        Usuario resultado = usuarioJpaAdapter.save(usuario);

        // Then
        assertNotNull(resultado);
        assertEquals(usuarioId, resultado.id());
        assertEquals("Juan Pérez", resultado.nombre());
        assertEquals("juan@example.com", resultado.email());
        assertEquals("password123", resultado.contrasena());
        assertTrue(resultado.activo());

        verify(usuarioRepository, times(1)).save(any(UsuarioJpaEntity.class));
    }

    @Test
    @DisplayName("Debería obtener usuario por email")
    void testFindByEmail() {
        // Given
        String email = "juan@example.com";
        UUID usuarioId = UUID.randomUUID();
        UsuarioJpaEntity entity = new UsuarioJpaEntity(
                usuarioId,
                "Juan Pérez",
                email,
                "password123",
                true,
                LocalDateTime.now()
        );

        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(entity));

        // When
        Optional<Usuario> resultado = usuarioJpaAdapter.findByEmail(email);

        // Then
        assertTrue(resultado.isPresent());
        assertEquals(usuarioId, resultado.get().id());
        assertEquals(email, resultado.get().email());

        verify(usuarioRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Debería retornar vacío si usuario no existe por email")
    void testFindByEmailNoExiste() {
        // Given
        String email = "noexiste@example.com";
        when(usuarioRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When
        Optional<Usuario> resultado = usuarioJpaAdapter.findByEmail(email);

        // Then
        assertTrue(resultado.isEmpty());

        verify(usuarioRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Debería obtener usuario por ID")
    void testFindById() {
        // Given
        UUID usuarioId = UUID.randomUUID();
        UsuarioJpaEntity entity = new UsuarioJpaEntity(
                usuarioId,
                "Juan Pérez",
                "juan@example.com",
                "password123",
                true,
                LocalDateTime.now()
        );

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(entity));

        // When
        Optional<Usuario> resultado = usuarioJpaAdapter.findById(usuarioId);

        // Then
        assertTrue(resultado.isPresent());
        assertEquals(usuarioId, resultado.get().id());

        verify(usuarioRepository, times(1)).findById(usuarioId);
    }

    @Test
    @DisplayName("Debería verificar que email existe")
    void testExistsByEmail() {
        // Given
        String email = "juan@example.com";
        when(usuarioRepository.existsByEmail(email)).thenReturn(true);

        // When
        boolean resultado = usuarioJpaAdapter.existsByEmail(email);

        // Then
        assertTrue(resultado);

        verify(usuarioRepository, times(1)).existsByEmail(email);
    }

    @Test
    @DisplayName("Debería verificar que email no existe")
    void testExistsByEmailFalse() {
        // Given
        String email = "noexiste@example.com";
        when(usuarioRepository.existsByEmail(email)).thenReturn(false);

        // When
        boolean resultado = usuarioJpaAdapter.existsByEmail(email);

        // Then
        assertFalse(resultado);

        verify(usuarioRepository, times(1)).existsByEmail(email);
    }
}

