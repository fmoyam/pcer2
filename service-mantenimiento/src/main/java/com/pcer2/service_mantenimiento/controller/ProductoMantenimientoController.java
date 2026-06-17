package com.pcer2.service_mantenimiento.controller;

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

import com.pcer2.service_mantenimiento.dto.ProductoMantenimientoDTO;
import com.pcer2.service_mantenimiento.model.ProductoMantenimiento;
import com.pcer2.service_mantenimiento.service.ProductoMantenimientoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/mantenimiento")
@CrossOrigin(origins = "*")
@Tag(name = "Gestión de Productos de Mantenimiento", description = "Endpoints para administrar productos e insumos de mantenimiento")
public class ProductoMantenimientoController {

    @Autowired
    private ProductoMantenimientoService productoMantenimientoService;

    @GetMapping
    @Operation(summary = "Listar productos de mantenimiento", description = "Obtiene todos los productos de mantenimiento registrados en el sistema")
    public List<ProductoMantenimiento> listar() {
        return productoMantenimientoService.listarTodos();
    }

    @PostMapping
    @Operation(summary = "Crear producto de mantenimiento", description = "Registra un nuevo producto o insumo de mantenimiento")
    public ResponseEntity<ProductoMantenimiento> crear(@RequestBody ProductoMantenimientoDTO productoMantenimientoDTO) {
        return ResponseEntity.ok(productoMantenimientoService.guardar(productoMantenimientoDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto de mantenimiento por ID", description = "Obtiene un producto de mantenimiento específico según su ID")
    public ResponseEntity<ProductoMantenimiento> obtenerPorId(@PathVariable Long id) {
        return productoMantenimientoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto de mantenimiento", description = "Actualiza los datos de un producto de mantenimiento existente")
    public ResponseEntity<ProductoMantenimiento> actualizar(@PathVariable Long id, @RequestBody ProductoMantenimientoDTO productoMantenimientoDTO) {
        ProductoMantenimiento productoActualizado = productoMantenimientoService.actualizarProducto(id, productoMantenimientoDTO);

        if (productoActualizado != null) {
            return ResponseEntity.ok(productoActualizado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto de mantenimiento", description = "Elimina un producto de mantenimiento según su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoMantenimientoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}