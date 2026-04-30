package com.gonzalofdezz.fitmanager.config;

import com.gonzalofdezz.fitmanager.application.ports.output.LoadClassesOutputPort;
import com.gonzalofdezz.fitmanager.application.usecases.GetClassesUseCase;
import com.gonzalofdezz.fitmanager.application.ports.GetClassesService;
import com.gonzalofdezz.fitmanager.application.ports.input.ClassesInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public GetClassesUseCase getClassesUseCase(LoadClassesOutputPort loadClassesPort) {
        return new GetClassesService(loadClassesPort);
    }

    @Bean
    public ClassesInputPort classesInputPort(LoadClassesOutputPort loadClassesPort) {
        return new GetClassesService(loadClassesPort);
    }
}
