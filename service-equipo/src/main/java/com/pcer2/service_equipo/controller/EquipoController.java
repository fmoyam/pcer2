package com.pcer2.service_equipo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pcer2.service_equipo.model.Equipo;
import com.pcer2.service_equipo.service.EquipoService;

@RestController
@RequestMapping("/api/v1/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public List<Equipo> listar() {
        return equipoService.listarTodos();
    }
    
    // Buscar equipo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> buscarPorId(@PathVariable Long id) {
        return equipoService.findById(id)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }

    // Obtener equipos por id cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Equipo>> getEquiposByClienteId(@PathVariable Long clienteId) {
        List<Equipo> equipos = equipoService.findByClienteId(clienteId);
        return ResponseEntity.ok(equipos);
    }    
    
    // Buscar equipo por número de serie
    @GetMapping("/serie/{numeroserie}")
    public ResponseEntity<Equipo> buscarPorNumeroSerie(@PathVariable String numeroserie) {
        return equipoService.buscarPorNumeroSerie(numeroserie)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }
    
    // Buscar por marca
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Equipo>> buscarPorMarca(@PathVariable String marca) {
        List<Equipo> equipos = equipoService.buscarPorMarca(marca);
        if (equipos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipos);
    }
    
    // Crear nuevo equipo
    @PostMapping
    public ResponseEntity<Equipo> crear(@RequestBody Equipo equipo) {
        try {
            Equipo nuevoEquipo = equipoService.guardar(equipo);
            return ResponseEntity.ok(nuevoEquipo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Método específico para crear equipo con clienteId
    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<Equipo> createEquipoForCliente(
            @PathVariable Long clienteId, 
            @RequestBody Equipo equipo) {
        equipo.setClienteId(clienteId);
        Equipo savedEquipo = equipoService.guardar(equipo);
        return new ResponseEntity<>(savedEquipo, HttpStatus.CREATED);
    }   
    
    // Actualizar equipo existente
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
    
    // Eliminar equipo
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
