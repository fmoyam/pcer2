package com.pcer2.service_servicio.config;

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
                // info general que se muestra arriba en Swagger
                .info(new Info()
                        .title("API PCer2 - Gestión de Servicios")
                        .version("1.0")
                        .description("Documentación del sistema PCer2"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))  // Indica que los endpoints usan seguridad tipo Bearer Token
                .components(new Components()  // Configura el tipo de seguridad JWT para Swagger
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer") // Esquema bearer, usado en JWT
                                        .bearerFormat("JWT"))); // Indica que el token es JWT
    }
}