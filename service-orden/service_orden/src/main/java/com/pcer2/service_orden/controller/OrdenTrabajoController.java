package com.pcer2.service_orden.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcer2.service_orden.dto.OrdenTrabajoDTO;
import com.pcer2.service_orden.model.OrdenTrabajo;
import com.pcer2.service_orden.service.OrdenTrabajoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // Esta clase recibirá peticiones web
@RequestMapping("/api/v1/ordenes") // Todos los endpoints empiezan con /api/v1/ordenes
@CrossOrigin(origins = "*")
@Tag(name = "Gestión de Órdenes de Trabajo", description = "Endpoints para administrar órdenes de trabajo del servicio técnico")

public class OrdenTrabajoController {

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;

    @GetMapping // Lista todas las órdenes
    @Operation(summary = "Listar órdenes de trabajo", description = "Obtiene todas las órdenes de trabajo registradas en el sistema") 
    public List<OrdenTrabajo> listar() {
        return ordenTrabajoService.listarTodos();
    }

    @PostMapping // Crea una orden nueva usando DTO
    @Operation(summary = "Crear orden de trabajo", description = "Registra una nueva orden de trabajo asociando cliente, equipo, servicio, software y producto de mantenimiento")
    public ResponseEntity<OrdenTrabajo> crear(@RequestBody OrdenTrabajoDTO ordenTrabajoDTO) {
        return ResponseEntity.ok(ordenTrabajoService.guardar(ordenTrabajoDTO));
    }

    @GetMapping("/{id}") // Busca una orden por id
    @Operation(summary = "Buscar orden por ID", description = "Obtiene una orden de trabajo específica y muestra datos externos mediante WebClient")
    public ResponseEntity<OrdenTrabajo> obtenerPorId(@PathVariable Long id) {
        return ordenTrabajoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}") // Modifica una orden existente usando DTO
    @Operation(summary = "Actualizar orden de trabajo", description = "Actualiza los datos de una orden de trabajo existente")
    public ResponseEntity<OrdenTrabajo> actualizar(@PathVariable Long id, @RequestBody OrdenTrabajoDTO ordenTrabajoDTO) {
        OrdenTrabajo ordenActualizada = ordenTrabajoService.actualizarOrden(id, ordenTrabajoDTO);

        if (ordenActualizada != null) {
            return ResponseEntity.ok(ordenActualizada);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}") // Elimina una orden existente
    @Operation(summary = "Eliminar orden de trabajo", description = "Elimina una orden de trabajo según su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ordenTrabajoService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }
}