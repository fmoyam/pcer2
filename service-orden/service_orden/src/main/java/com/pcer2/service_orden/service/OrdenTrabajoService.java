package com.pcer2.service_orden.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcer2.service_orden.dto.OrdenTrabajoDTO;
import com.pcer2.service_orden.model.OrdenTrabajo;
import com.pcer2.service_orden.repository.OrdenTrabajoRepository;

@Service
public class OrdenTrabajoService {

    @Autowired
    private OrdenTrabajoRepository ordenTrabajoRepository;

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
        return ordenTrabajoRepository.findById(id);
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
}