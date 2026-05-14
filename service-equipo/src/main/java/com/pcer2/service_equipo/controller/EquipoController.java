package com.pcer2.service_equipo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v2/equipos")
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
        return equipoService.buscarPorId(id)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
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
    
    // Actualizar equipo existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Equipo equipoActualizado) {
        // Buscar el equipo existente
        Equipo equipoExistente = equipoService.buscarPorId(id).orElse(null);
        
        if (equipoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Actualizar solo los campos que vienen en la petición
        if (equipoActualizado.getTipoEquipo() != null) {
            equipoExistente.setTipoEquipo(equipoActualizado.getTipoEquipo());
        }
        if (equipoActualizado.getMarca() != null) {
            equipoExistente.setMarca(equipoActualizado.getMarca());
        }
        if (equipoActualizado.getModelo_cpu() != null) {
            equipoExistente.setModelo_cpu(equipoActualizado.getModelo_cpu());
        }
        if (equipoActualizado.getPlaca_madre() != null) {
            equipoExistente.setPlaca_madre(equipoActualizado.getPlaca_madre());
        }
        if (equipoActualizado.getRam_cantidad() > 0) {
            equipoExistente.setRam_cantidad(equipoActualizado.getRam_cantidad());
        }
        if (equipoActualizado.getRam_frecuencia() > 0) {
            equipoExistente.setRam_frecuencia(equipoActualizado.getRam_frecuencia());
        }
        if (equipoActualizado.getAlmacen_cantidad() > 0) {
            equipoExistente.setAlmacen_cantidad(equipoActualizado.getAlmacen_cantidad());
        }
        if (equipoActualizado.getAlmacen_tipo() != null) {
            equipoExistente.setAlmacen_tipo(equipoActualizado.getAlmacen_tipo());
        }
        if (equipoActualizado.getNumeroserie() != null) {
            // Verificar que el nuevo número de serie no esté en uso por otro equipo
            if (!equipoExistente.getNumeroserie().equals(equipoActualizado.getNumeroserie()) &&
                equipoService.existePorNumeroserie(equipoActualizado.getNumeroserie())) {
                return ResponseEntity
                        .badRequest()
                        .body("Ya existe otro equipo con el número de serie: " + equipoActualizado.getNumeroserie());
            }
            equipoExistente.setNumeroserie(equipoActualizado.getNumeroserie());
        }
        if (equipoActualizado.getVeces_reparado() >= 0) {
            equipoExistente.setVeces_reparado(equipoActualizado.getVeces_reparado());
        }
        
        // Guardar los cambios
        Equipo actualizado = equipoService.guardar(equipoExistente);
        return ResponseEntity.ok(actualizado);
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
