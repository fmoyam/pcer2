package com.pcer2.clientes.service;

import com.pcer2.clientes.dto.EquipoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Collections;
import java.util.List;

@Service
public class EquipoClienteService {

    @Autowired
    private WebClient webClient;

    public List<EquipoDTO> getEquiposByClienteId(Long clienteId) {
        try {
            return webClient.get()
                    .uri("/api/v1/equipos/cliente/{clienteId}", clienteId)
                    .retrieve()
                    .bodyToMono(new org.springframework.core.ParameterizedTypeReference<List<EquipoDTO>>() {})
                    .block();
        } catch (Exception e) {
            // si no responde manda una lista vacía conn el msj
            System.err.println("Error al conectar con service-equipo: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
