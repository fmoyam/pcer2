package com.pcer2.service_servicio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcer2.service_servicio.model.Servicio;
import com.pcer2.service_servicio.repository.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    //Guarda un servicio nuevo.
    public Servicio guardar(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public List<Servicio> listarTodos() {
        return servicioRepository.findAll();
    }

    //Busca un servicio por su id
    public Optional<Servicio> buscarPorId(Long id) { //Usamos Optional porque puede pasar que el servicio exista o no
        return servicioRepository.findById(id);
    }

    //Primero busca el servicio por id para actualizar
    public Servicio actualizarServicio(Long id, Servicio servicioActualizado) {
        Optional<Servicio> servicioExistente = servicioRepository.findById(id);

        //Si existe, cambia sus datos
        if (servicioExistente.isPresent()) {
            Servicio servicio = servicioExistente.get();

            servicio.setNombre(servicioActualizado.getNombre());
            servicio.setDescripcion(servicioActualizado.getDescripcion());
            servicio.setPrecioBase(servicioActualizado.getPrecioBase());
            servicio.setActivo(servicioActualizado.getActivo());

            return servicioRepository.save(servicio);
        }
        //Si no existe, devuelve null
        return null;
    }

    //Elimina un servicio según su id
    public void eliminarServicio(Long id) {
        servicioRepository.deleteById(id);
    }
}
