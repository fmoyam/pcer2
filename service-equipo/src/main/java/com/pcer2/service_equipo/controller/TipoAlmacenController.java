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

import com.pcer2.service_equipo.model.TipoAlmacen;
import com.pcer2.service_equipo.service.TipoAlmacenService;

@RestController
@RequestMapping("/api/v1/tipoalmacen")
public class TipoAlmacenController {

    @Autowired
    private TipoAlmacenService tipoAlmacenService;

    @GetMapping
    public ResponseEntity<List<TipoAlmacen>> listarTodos() {
        return ResponseEntity.ok(tipoAlmacenService.listarTiposAlmacen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoAlmacen> buscarPorId(@PathVariable Long id) {
        return tipoAlmacenService.buscarTipoAlmacenPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<TipoAlmacen> buscarPorNombre(@PathVariable String nombre) {
        return tipoAlmacenService.buscarTipoAlmacenPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody TipoAlmacen tipoAlmacen) {
        if (tipoAlmacenService.existePorNombre(tipoAlmacen.getNombre())) {
            return ResponseEntity.badRequest()
                    .body("Ya existe un tipo de almacenamiento con el nombre: " + tipoAlmacen.getNombre());
        }
        TipoAlmacen nuevo = tipoAlmacenService.guardarTipoAlmacen(tipoAlmacen);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody TipoAlmacen tipoAlmacenActualizado) {
        return tipoAlmacenService.buscarTipoAlmacenPorId(id)
                .map(almacenExistente -> {
                    almacenExistente.setNombre(tipoAlmacenActualizado.getNombre());
                    TipoAlmacen actualizado = tipoAlmacenService.guardarTipoAlmacen(almacenExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (tipoAlmacenService.buscarTipoAlmacenPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tipoAlmacenService.eliminarTipoAlmacen(id);
        return ResponseEntity.noContent().build();
    }
}