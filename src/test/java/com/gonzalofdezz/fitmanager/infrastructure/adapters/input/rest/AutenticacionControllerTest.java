package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.dto.LoginDTO;
import com.gonzalofdezz.fitmanager.application.dto.RegistroDTO;
import com.gonzalofdezz.fitmanager.application.ports.input.AutenticacionInputPort;
import com.gonzalofdezz.fitmanager.domain.entity.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AutenticacionController.class)
@DisplayName("AutenticacionController Tests")
class AutenticacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AutenticacionInputPort autenticacionInputPort;

    @Test
    @DisplayName("Debería registrar un nuevo usuario exitosamente")
    void testRegistrarExitoso() throws Exception {
        // Given
        RegistroDTO request = new RegistroDTO("Juan Pérez", "juan@example.com", "password123");
        UUID usuarioId = UUID.randomUUID();
        Usuario usuario = new Usuario(
                usuarioId,
                "Juan Pérez",
                "juan@example.com",
                "password123",
                true,
                LocalDateTime.now()
        );

        when(autenticacionInputPort.registrar(anyString(), anyString(), anyString()))
                .thenReturn(usuario);

        // When & Then
        mockMvc.perform(post("/auth/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(usuarioId.toString()))
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$.email").value("juan@example.com"))
                .andExpect(jsonPath("$.activo").value(true));

        verify(autenticacionInputPort, times(1)).registrar("Juan Pérez", "juan@example.com", "password123");
    }

    @Test
    @DisplayName("Debería fallar registro con email inválido")
    void testRegistrarEmailInvalido() throws Exception {
        // Given
        RegistroDTO request = new RegistroDTO("Juan Pérez", "emailinvalido", "password123");

        // When & Then
        mockMvc.perform(post("/auth/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(autenticacionInputPort, never()).registrar(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Debería fallar registro con contraseña muy corta")
    void testRegistrarContraseñaCorta() throws Exception {
        // Given
        RegistroDTO request = new RegistroDTO("Juan Pérez", "juan@example.com", "123");

        // When & Then
        mockMvc.perform(post("/auth/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(autenticacionInputPort, never()).registrar(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Debería hacer login exitosamente")
    void testLoginExitoso() throws Exception {
        // Given
        LoginDTO request = new LoginDTO("juan@example.com", "password123");
        UUID usuarioId = UUID.randomUUID();
        Usuario usuario = new Usuario(
                usuarioId,
                "Juan Pérez",
                "juan@example.com",
                "password123",
                true,
                LocalDateTime.now()
        );

        when(autenticacionInputPort.login("juan@example.com", "password123"))
                .thenReturn(Optional.of(usuario));

        // When & Then
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(usuarioId.toString()))
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$.email").value("juan@example.com"))
                .andExpect(jsonPath("$.activo").value(true));

        verify(autenticacionInputPort, times(1)).login("juan@example.com", "password123");
    }

    @Test
    @DisplayName("Debería fallar login con credenciales incorrectas")
    void testLoginCredencialesIncorrectas() throws Exception {
        // Given
        LoginDTO request = new LoginDTO("juan@example.com", "wrongPassword");

        when(autenticacionInputPort.login("juan@example.com", "wrongPassword"))
                .thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());

        verify(autenticacionInputPort, times(1)).login("juan@example.com", "wrongPassword");
    }

    @Test
    @DisplayName("Debería obtener usuario por email")
    void testObtenerPorEmail() throws Exception {
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

        when(autenticacionInputPort.obtenerPorEmail(email))
                .thenReturn(Optional.of(usuario));

        // When & Then
        mockMvc.perform(get("/auth/usuarios/{email}", email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(usuarioId.toString()))
                .andExpect(jsonPath("$.email").value(email));

        verify(autenticacionInputPort, times(1)).obtenerPorEmail(email);
    }

    @Test
    @DisplayName("Debería retornar 404 si usuario no existe")
    void testObtenerPorEmailNoExiste() throws Exception {
        // Given
        String email = "noexiste@example.com";

        when(autenticacionInputPort.obtenerPorEmail(email))
                .thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/auth/usuarios/{email}", email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(autenticacionInputPort, times(1)).obtenerPorEmail(email);
    }

    @Test
    @DisplayName("Debería verificar que email existe")
    void testEmailExiste() throws Exception {
        // Given
        String email = "juan@example.com";

        when(autenticacionInputPort.emailExiste(email))
                .thenReturn(true);

        // When & Then
        mockMvc.perform(get("/auth/usuarios/{email}/existe", email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(autenticacionInputPort, times(1)).emailExiste(email);
    }

    @Test
    @DisplayName("Debería verificar que email no existe")
    void testEmailNoExiste() throws Exception {
        // Given
        String email = "noexiste@example.com";

        when(autenticacionInputPort.emailExiste(email))
                .thenReturn(false);

        // When & Then
        mockMvc.perform(get("/auth/usuarios/{email}/existe", email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        verify(autenticacionInputPort, times(1)).emailExiste(email);
    }
}

