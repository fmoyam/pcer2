package com.pcer2.service_servicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcer2.service_servicio.model.Servicio;
import com.pcer2.service_servicio.service.ServicioService;

@RestController //Esta clase recibirá peticiones web
@RequestMapping("/api/v1/servicios") //Todos los endpoints empiezan con /api/v1/servicios
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping //Sirve para listar todos los servicios
    public List<Servicio> listar() {
        return servicioService.listarTodos();
    }

    @PostMapping //Sirve para crear un servicio nuevo
    public ResponseEntity<Servicio> crear(@RequestBody Servicio servicio) {
        return ResponseEntity.ok(servicioService.guardar(servicio));
    }

    @GetMapping("/{id}") // Sirve para buscar un servicio específico
    public ResponseEntity<Servicio> obtenerPorId(@PathVariable Long id) {
        return servicioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}") // Sirve para modificar un servicio existente
    public ResponseEntity<Servicio> actualizar(@PathVariable Long id, @RequestBody Servicio servicio) {
        Servicio servicioActualizado = servicioService.actualizarServicio(id, servicio);

        if (servicioActualizado != null) {
            return ResponseEntity.ok(servicioActualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}") // Sirve para eliminar un servicio existente
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
        return ResponseEntity.noContent().build();
    }
}
