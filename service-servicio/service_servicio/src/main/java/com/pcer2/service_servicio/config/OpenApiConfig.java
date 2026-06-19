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
                // Información general que se muestra arriba en Swagger
                .info(new Info()
                        .title("API Servicio Técnico")
                        .version("1.0")
                        .description("Documentación del microservicio de servicios técnicos"))

                // Indica que los endpoints usan seguridad tipo Bearer Token
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))

                // Configura el tipo de seguridad JWT para Swagger
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        // Nombre interno de la seguridad
                                        .name("bearerAuth")

                                        // Tipo HTTP porque se envía por header Authorization
                                        .type(SecurityScheme.Type.HTTP)

                                        // Esquema bearer, usado en JWT
                                        .scheme("bearer")

                                        // Indica que el token es JWT
                                        .bearerFormat("JWT")));
    }
}