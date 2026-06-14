package com.pcer2.service_mantenimiento.controller;

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

import com.pcer2.service_mantenimiento.dto.ProductoMantenimientoDTO;
import com.pcer2.service_mantenimiento.model.ProductoMantenimiento;
import com.pcer2.service_mantenimiento.service.ProductoMantenimientoService;

@RestController
@RequestMapping("/api/v1/mantenimiento")
public class ProductoMantenimientoController {

    @Autowired
    private ProductoMantenimientoService productoMantenimientoService;

    @GetMapping
    public List<ProductoMantenimiento> listar() {
        return productoMantenimientoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<ProductoMantenimiento> crear(@RequestBody ProductoMantenimientoDTO productoMantenimientoDTO) {
        return ResponseEntity.ok(productoMantenimientoService.guardar(productoMantenimientoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoMantenimiento> obtenerPorId(@PathVariable Long id) {
        return productoMantenimientoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoMantenimiento> actualizar(@PathVariable Long id, @RequestBody ProductoMantenimientoDTO productoMantenimientoDTO) {
        ProductoMantenimiento productoActualizado = productoMantenimientoService.actualizarProducto(id, productoMantenimientoDTO);

        if (productoActualizado != null) {
            return ResponseEntity.ok(productoActualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoMantenimientoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}