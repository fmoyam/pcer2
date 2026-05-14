package com.pcer2.service_orden.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.pcer2.service_orden.dto.OrdenTrabajoDTO;
import com.pcer2.service_orden.model.OrdenTrabajo;
import com.pcer2.service_orden.repository.OrdenTrabajoRepository;

@Service
public class OrdenTrabajoService {

    @Autowired
    private OrdenTrabajoRepository ordenTrabajoRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Guarda una orden nueva usando DTO
    public OrdenTrabajo guardar(OrdenTrabajoDTO ordenTrabajoDTO) {

        OrdenTrabajo ordenTrabajo = new OrdenTrabajo();

        ordenTrabajo.setClienteId(ordenTrabajoDTO.getClienteId());
        ordenTrabajo.setEquipoId(ordenTrabajoDTO.getEquipoId());
        ordenTrabajo.setServicioId(ordenTrabajoDTO.getServicioId());
        ordenTrabajo.setDescripcionProblema(ordenTrabajoDTO.getDescripcionProblema());
        ordenTrabajo.setFechaIngreso(ordenTrabajoDTO.getFechaIngreso());
        ordenTrabajo.setFechaEntregaEstimada(ordenTrabajoDTO.getFechaEntregaEstimada());
        ordenTrabajo.setEstado(ordenTrabajoDTO.getEstado());
        ordenTrabajo.setPrecioTotal(ordenTrabajoDTO.getPrecioTotal());

        return ordenTrabajoRepository.save(ordenTrabajo);
    }

    // Lista todas las órdenes
    public List<OrdenTrabajo> listarTodos() {
        return ordenTrabajoRepository.findAll();
    }

    // Busca una orden por id
    public Optional<OrdenTrabajo> buscarPorId(Long id) {
        Optional<OrdenTrabajo> orden = ordenTrabajoRepository.findById(id);

        if (orden.isPresent()) {
            OrdenTrabajo ordenConServicio = agregarDatosServicio(orden.get());
            return Optional.of(ordenConServicio);
        }

        return Optional.empty();
    }

    // Actualiza una orden existente usando DTO
    public OrdenTrabajo actualizarOrden(Long id, OrdenTrabajoDTO ordenTrabajoDTO) {
        Optional<OrdenTrabajo> ordenExistente = ordenTrabajoRepository.findById(id);

        if (ordenExistente.isPresent()) {
            OrdenTrabajo ordenTrabajo = ordenExistente.get();

            ordenTrabajo.setClienteId(ordenTrabajoDTO.getClienteId());
            ordenTrabajo.setEquipoId(ordenTrabajoDTO.getEquipoId());
            ordenTrabajo.setServicioId(ordenTrabajoDTO.getServicioId());
            ordenTrabajo.setDescripcionProblema(ordenTrabajoDTO.getDescripcionProblema());
            ordenTrabajo.setFechaIngreso(ordenTrabajoDTO.getFechaIngreso());
            ordenTrabajo.setFechaEntregaEstimada(ordenTrabajoDTO.getFechaEntregaEstimada());
            ordenTrabajo.setEstado(ordenTrabajoDTO.getEstado());
            ordenTrabajo.setPrecioTotal(ordenTrabajoDTO.getPrecioTotal());

            return ordenTrabajoRepository.save(ordenTrabajo);
        }

        return null;
    }

    // Elimina una orden por id
    public void eliminarOrden(Long id) {
        ordenTrabajoRepository.deleteById(id);
    }

    // Método auxiliar para consultar el servicio asociado
    private OrdenTrabajo agregarDatosServicio(OrdenTrabajo ordenTrabajo) {

        if (ordenTrabajo.getServicioId() != null) {
            try {
                Object datosServicio = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8083/api/v1/servicios/" + ordenTrabajo.getServicioId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();

                ordenTrabajo.setDatosServicio(datosServicio);

            } catch (Exception e) {
                ordenTrabajo.setDatosServicio("No se pudieron obtener los datos del servicio");
            }
        }

        return ordenTrabajo;
    }
}