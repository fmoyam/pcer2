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

import com.pcer2.service_equipo.model.TipoEquipo;
import com.pcer2.service_equipo.service.TipoEquipoService;

@RestController
@RequestMapping("/api/v2/tipoequipo")
public class TipoEquipoController {

    @Autowired
    private TipoEquipoService tipoEquipoService;

    @GetMapping
    public ResponseEntity<List<TipoEquipo>> listarTiposEquipo() {
        return ResponseEntity.ok(tipoEquipoService.listarTiposEquipo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEquipo> buscarTipoEquipoId(@PathVariable Long id) {
        return tipoEquipoService.buscarTipoEquipoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<TipoEquipo> buscarPorNombre(@PathVariable String nombre) {
        return tipoEquipoService.buscarTipoEquipoPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody TipoEquipo tipoEquipo) {
        if (tipoEquipoService.existePorNombre(tipoEquipo.getNombre())) {
            return ResponseEntity.badRequest()
                    .body("Ya existe un tipo de equipo con el nombre: " + tipoEquipo.getNombre());
        }
        TipoEquipo nuevo = tipoEquipoService.guardarTipoEquipo(tipoEquipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody TipoEquipo tipoEquipoActualizado) {
        return tipoEquipoService.buscarTipoEquipoPorId(id)
                .map(tipoExistente -> {
                    tipoExistente.setNombre(tipoEquipoActualizado.getNombre());
                    TipoEquipo actualizado = tipoEquipoService.guardarTipoEquipo(tipoExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (tipoEquipoService.buscarTipoEquipoPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tipoEquipoService.eliminarTipoEquipo(id);
        return ResponseEntity.noContent().build();
    }
}
