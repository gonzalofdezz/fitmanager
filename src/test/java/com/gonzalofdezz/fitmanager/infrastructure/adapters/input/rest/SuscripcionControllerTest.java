package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.usecases.ObtenerSuscripcionUseCase;
import com.gonzalofdezz.fitmanager.domain.entity.Suscripcion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuscripcionController.class)
class SuscripcionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ObtenerSuscripcionUseCase obtenerSuscripcionUseCase;

    @Test
    void shouldReturnSuscripcionWhenFound() throws Exception {
        UUID id = UUID.fromString("550e8400-e29b-41d4-a716-446655440002");
        UUID usuarioId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

        when(obtenerSuscripcionUseCase.obtenerPorUsuarioId(usuarioId)).thenReturn(Optional.of(
                new Suscripcion(
                        id,
                        usuarioId,
                        "PREMIUM",
                        LocalDateTime.of(2026, 1, 1, 0, 0),
                        LocalDateTime.of(2026, 12, 31, 23, 59, 59),
                        true,
                        LocalDateTime.of(2026, 1, 1, 10, 0)
                )
        ));

        mockMvc.perform(get("/suscripciones/{usuarioId}", usuarioId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.usuarioId").value(usuarioId.toString()))
                .andExpect(jsonPath("$.tipoPlan").value("PREMIUM"))
                .andExpect(jsonPath("$.activa").value(true));
    }

    @Test
    void shouldReturnNotFoundWhenSuscripcionDoesNotExist() throws Exception {
        UUID usuarioId = UUID.fromString("550e8400-e29b-41d4-a716-000000000000");
        when(obtenerSuscripcionUseCase.obtenerPorUsuarioId(usuarioId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/suscripciones/{usuarioId}", usuarioId))
                .andExpect(status().isNotFound());
    }
}
