package com.pcer2.service_auth.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                // info general que se muestra arriba en Swagger
                .info(new Info()
                        .title("API PCer2 - Sistema de autenticación")
                        .version("1.0")
                        .description("Documentación de Servicio Auth de PCer2"));
    }
}
