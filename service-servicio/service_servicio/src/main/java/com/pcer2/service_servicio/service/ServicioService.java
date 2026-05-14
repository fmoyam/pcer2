package com.pcer2.service_servicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcer2.service_servicio.dto.ServicioDTO;
import com.pcer2.service_servicio.model.Servicio;
import com.pcer2.service_servicio.repository.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    // Guarda un servicio nuevo usando DTO
    public Servicio guardar(ServicioDTO servicioDTO) {

        Servicio servicio = new Servicio();

        servicio.setNombre(servicioDTO.getNombre());
        servicio.setDescripcion(servicioDTO.getDescripcion());
        servicio.setPrecioBase(servicioDTO.getPrecioBase());
        servicio.setActivo(servicioDTO.getActivo());

        return servicioRepository.save(servicio);
    }

    // Lista todos los servicios
    public List<Servicio> listarTodos() {
        return servicioRepository.findAll();
    }

    // Busca un servicio por su id
    public Optional<Servicio> buscarPorId(Long id) {
        return servicioRepository.findById(id);
    }

    // Actualiza un servicio existente usando DTO
    public Servicio actualizarServicio(Long id, ServicioDTO servicioDTO) {
        Optional<Servicio> servicioExistente = servicioRepository.findById(id);

        if (servicioExistente.isPresent()) {
            Servicio servicio = servicioExistente.get();

            servicio.setNombre(servicioDTO.getNombre());
            servicio.setDescripcion(servicioDTO.getDescripcion());
            servicio.setPrecioBase(servicioDTO.getPrecioBase());
            servicio.setActivo(servicioDTO.getActivo());

            return servicioRepository.save(servicio);
        }

        return null;
    }

    // Elimina un servicio según su id
    public void eliminarServicio(Long id) {
        servicioRepository.deleteById(id);
    }
}