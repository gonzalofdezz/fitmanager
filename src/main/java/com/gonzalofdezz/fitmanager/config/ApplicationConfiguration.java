package com.gonzalofdezz.fitmanager.config;

import com.gonzalofdezz.fitmanager.application.ports.output.AthleteRepositoryPort;
import com.gonzalofdezz.fitmanager.application.usecases.AthleteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public AthleteService athleteService(AthleteRepositoryPort athleteRepositoryPort) {
        return new AthleteService(athleteRepositoryPort);
    }
}
