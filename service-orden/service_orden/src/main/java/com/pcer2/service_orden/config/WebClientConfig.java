package com.pcer2.service_orden.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration //Esta clase tiene configuraciones del proyecto
public class WebClientConfig {

    @Bean //Guarda este objeto para poder usarlo después en otras clases
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}