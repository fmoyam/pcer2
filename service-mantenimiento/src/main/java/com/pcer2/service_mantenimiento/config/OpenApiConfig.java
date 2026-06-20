package com.pcer2.service_mantenimiento.config;

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
                // Información principal que aparece en Swagger
                .info(new Info()
                        .title("API Productos de Mantenimiento")
                        .version("1.0")
                        .description("Documentación del microservicio de productos e insumos de mantenimiento"))

                // Indica que Swagger debe considerar autenticación JWT
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))

                // Define el esquema Bearer Token para JWT
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}