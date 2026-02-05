package com.gonzalofdezz.fitmanager.config;

import com.gonzalofdezz.fitmanager.gym.application.ports.output.GymRepositoryPort;
import com.gonzalofdezz.fitmanager.gym.application.usecases.GymService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public GymService gymService(GymRepositoryPort gymRepositoryPort) {
        return new GymService(gymRepositoryPort);
    }
}