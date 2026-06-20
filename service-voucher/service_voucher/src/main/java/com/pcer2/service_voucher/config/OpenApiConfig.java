package com.pcer2.service_voucher.config;

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
                // Datos que se mostrarán en la parte superior de Swagger
                .info(new Info()
                        .title("API PCer2 - Gestión de Vouchers")
                        .version("1.0")
                        .description("Documentación del microservicio de vouchers para clientes de PCer2"))

                // Le dice a Swagger que la API usa token JWT
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))

                // Configuración para que aparezca el botón Authorize
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}