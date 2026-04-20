package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import com.gonzalofdezz.fitmanager.application.ports.input.ClassesInputPort;
import com.gonzalofdezz.fitmanager.application.usecases.GetClassesUseCase;
import com.gonzalofdezz.fitmanager.domain.entity.GymClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClassController.class)
class ClassControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetClassesUseCase getClassesUseCase;

    @MockBean
    private ClassesInputPort classesInputPort;

    @Test
    void shouldReturnMappedClasses() throws Exception {
        when(getClassesUseCase.execute()).thenReturn(List.of(
                new GymClass(1L, "Yoga", "Clase suave", "BEGINNER", 60, 20, 1L),
                new GymClass(2L, "Boxeo", "Clase intensa", "ADVANCED", 45, 15, 1L)
        ));

        mockMvc.perform(get("/clases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Yoga"))
                .andExpect(jsonPath("$[0].descripcion").value("Clase suave"))
                .andExpect(jsonPath("$[0].nivel").value("BEGINNER"))
                .andExpect(jsonPath("$[0].duracionMinutos").value(60))
                .andExpect(jsonPath("$[0].capacidadPorDefecto").value(20))
                .andExpect(jsonPath("$[1].nombre").value("Boxeo"));
    }
}
