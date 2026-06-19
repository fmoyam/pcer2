package com.pcer2.service_equipo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pcer2.service_equipo.model.Equipo;
import com.pcer2.service_equipo.service.EquipoService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Operation(summary = "Lista todos los equipos", description = "Obtiene todos los equipos presentes en BD 'pc_equipos'.")
    @GetMapping
    public List<Equipo> listar() {
        return equipoService.listarTodos();
    }
    
    @Operation(summary = "Busca un equipo mediante su ID", description = "Filtra equipos mediante ID en la BD 'pc_equipos'.")
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> buscarPorId(@PathVariable Long id) {
        return equipoService.findById(id)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Lista todos los equipos de una ID de cliente especifico", description = "Filtra equipos mediante el ID de su dueño y retorna la lista.")
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Equipo>> getEquiposByClienteId(@PathVariable Long clienteId) {
        List<Equipo> equipos = equipoService.findByClienteId(clienteId);
        return ResponseEntity.ok(equipos);
    }    
    
    @Operation(summary = "Busca un equipo mediante su numero de serie", description = "Filtra equipos mediante su serial en la BD 'pc_equipos'.")
    @GetMapping("/serie/{numeroserie}")
    public ResponseEntity<Equipo> buscarPorNumeroSerie(@PathVariable String numeroserie) {
        return equipoService.buscarPorNumeroSerie(numeroserie)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Busca equipos mediante su marca", description = "Filtra equipos por fabricante en la BD 'pc_equipos' y retorna la lista.")
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Equipo>> buscarPorMarca(@PathVariable String marca) {
        List<Equipo> equipos = equipoService.buscarPorMarca(marca);
        if (equipos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipos);
    }
    
    @Operation(summary = "Crea un nuevo equipo", description = "Guarda los datos del nuevo equipo en BD 'pc_equipos'.")
    @PostMapping
    public ResponseEntity<Equipo> crear(@RequestBody Equipo equipo) {
        try {
            Equipo nuevoEquipo = equipoService.guardar(equipo);
            return ResponseEntity.ok(nuevoEquipo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Crea un nuevo equipo para un cliente especifico", description = "Guarda el nuevo equipo y lo vincula a un cliente de inmediato.")
    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<Equipo> createEquipoForCliente(
            @PathVariable Long clienteId, 
            @RequestBody Equipo equipo) {
        equipo.setClienteId(clienteId);
        Equipo savedEquipo = equipoService.guardar(equipo);
        return new ResponseEntity<>(savedEquipo, HttpStatus.CREATED);
    }   
    
    @Operation(summary = "Actualiza los datos de un equipo existente", description = "Se busca un equipo mediante su ID y se actualizan sus datos.")
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        return equipoService.findById(id)
                .map(existingEquipo -> {
                    equipo.setId(id);
                    Equipo updatedEquipo = equipoService.guardar(equipo);
                    return ResponseEntity.ok(updatedEquipo);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Elimina un equipo mediante su ID", description = "Elimina los datos de un equipo en BD 'pc_equipos'.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            equipoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
