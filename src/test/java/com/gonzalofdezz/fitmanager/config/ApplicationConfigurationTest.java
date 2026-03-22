package com.gonzalofdezz.fitmanager.config;

import com.gonzalofdezz.fitmanager.application.ports.GetClassesService;
import com.gonzalofdezz.fitmanager.application.ports.output.LoadClassesOutputPort;
import com.gonzalofdezz.fitmanager.application.usecases.GetClassesUseCase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ApplicationConfigurationTest {

    private final ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();

    @Test
    void shouldCreateGetClassesUseCaseBean() {
        LoadClassesOutputPort loadClassesOutputPort = mock(LoadClassesOutputPort.class);

        GetClassesUseCase result = applicationConfiguration.getClassesUseCase(loadClassesOutputPort);

        assertThat(result).isInstanceOf(GetClassesService.class);
    }
}
