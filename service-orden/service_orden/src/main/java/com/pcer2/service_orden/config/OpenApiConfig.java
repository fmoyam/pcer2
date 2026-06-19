package com.pcer2.service_orden.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                // Información visible en Swagger
                .info(new Info()
                        .title("API Órdenes de Trabajo")
                        .version("1.0")
                        .description("Documentación del microservicio de órdenes de trabajo"))

                // Agrega seguridad Bearer Token a la documentación
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))

                // Define cómo Swagger debe enviar el token JWT
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}