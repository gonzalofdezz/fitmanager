package com.gonzalofdezz.fitmanager.config;

import com.gonzalofdezz.fitmanager.application.ports.output.LoadClassesOutputPort;
import com.gonzalofdezz.fitmanager.application.usecases.GetClassesUseCase;
import com.gonzalofdezz.fitmanager.application.ports.GetClassesService;
import com.gonzalofdezz.fitmanager.application.ports.input.ClassesInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {

    @Bean
    public GetClassesUseCase getClassesUseCase(LoadClassesOutputPort loadClassesPort) {
        return new GetClassesService(loadClassesPort);
    }

    @Bean
    public ClassesInputPort classesInputPort(LoadClassesOutputPort loadClassesPort) {
        return new GetClassesService(loadClassesPort);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5177",
                        "http://localhost:3000",
                        "http://localhost:5173",
                        "http://127.0.0.1:5177",
                        "http://127.0.0.1:3000",
                        "http://127.0.0.1:5173"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
