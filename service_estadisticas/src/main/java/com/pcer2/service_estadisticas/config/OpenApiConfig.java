package com.pcer2.service_estadisticas.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API PCer2 - Gestion de Estadisticas")
                        .version("1.0")
                        .description("Documentación del sistema PCer2"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor a través del Gateway")
                ));
    }

}
