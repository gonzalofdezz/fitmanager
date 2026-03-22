package com.gonzalofdezz.fitmanager.application.ports;

import com.gonzalofdezz.fitmanager.application.ports.output.LoadClassesOutputPort;
import com.gonzalofdezz.fitmanager.domain.entity.GymClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetClassesServiceTest {

    @Mock
    private LoadClassesOutputPort loadClassesOutputPort;

    @InjectMocks
    private GetClassesService getClassesService;

    @Test
    void shouldReturnClassesFromOutputPort() {
        List<GymClass> expected = List.of(new GymClass(1L, "Yoga", "Clase", "BEGINNER", 60, 20));
        when(loadClassesOutputPort.findAll()).thenReturn(expected);

        List<GymClass> result = getClassesService.execute();

        assertThat(result).isSameAs(expected);
        verify(loadClassesOutputPort).findAll();
    }
}
