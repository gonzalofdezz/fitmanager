package com.gonzalofdezz.fitmanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Fitmanager API",
                version = "v1",
                description = "API de Fitmanager (estructura hexagonal)"
        )
)
public class SwaggerConfig {
}
