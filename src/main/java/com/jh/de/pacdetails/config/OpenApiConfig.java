package com.jh.de.pacdetails.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"dev || local"})
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(getApiInfo());
    }
    private Info getApiInfo() {
        return new Info().title("PAC Details")
                .description("Retrieve pac and dividend data for LIFE policy")
                .version("v1");
    }
}
