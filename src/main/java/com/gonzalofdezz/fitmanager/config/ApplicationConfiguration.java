package com.gonzalofdezz.fitmanager.config;

import com.gonzalofdezz.fitmanager.application.usecases.GetClassesUseCase;
import com.gonzalofdezz.fitmanager.application.ports.output.LoadClassesPort;
import com.gonzalofdezz.fitmanager.application.ports.input.GetClassesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public GetClassesUseCase getClassesUseCase(LoadClassesPort loadClassesPort) {
        return new GetClassesService(loadClassesPort);
    }
}
