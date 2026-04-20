package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonzalofdezz.fitmanager.application.dto.CrearReservaDTO;
import com.gonzalofdezz.fitmanager.application.ports.input.ReservasInputPort;
import com.gonzalofdezz.fitmanager.application.usecases.CrearReservaUseCase;
import com.gonzalofdezz.fitmanager.domain.entity.Reserva;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservaController.class)
class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CrearReservaUseCase crearReservaUseCase;

    @MockBean
    private ReservasInputPort reservasInputPort;

    @Test
    void shouldCreateReservaAndReturnResponseBody() throws Exception {
        UUID reservaId = UUID.fromString("550e8400-e29b-41d4-a716-446655440001");
        UUID usuarioId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
        LocalDateTime fechaReserva = LocalDateTime.of(2026, 3, 25, 10, 30);
        LocalDateTime fechaCreacion = LocalDateTime.of(2026, 3, 22, 13, 16, 43);

        when(crearReservaUseCase.crear(eq(usuarioId), eq(1L), eq(fechaReserva)))
                .thenReturn(new Reserva(reservaId, usuarioId, 1L, fechaReserva, fechaCreacion));

        CrearReservaDTO request = new CrearReservaDTO(usuarioId, 1L, fechaReserva);

        mockMvc.perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(reservaId.toString()))
                .andExpect(jsonPath("$.usuarioId").value(usuarioId.toString()))
                .andExpect(jsonPath("$.claseId").value(1))
                .andExpect(jsonPath("$.fechaReserva").value("2026-03-25T10:30:00"))
                .andExpect(jsonPath("$.fechaCreacion").value("2026-03-22T13:16:43"));
    }
}
