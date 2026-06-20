package com.pcer2.service_descuento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcer2.service_descuento.dto.DescuentoDto;
import com.pcer2.service_descuento.model.Descuento;
import com.pcer2.service_descuento.service.DescuentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/descuento")
@Tag(name = "Descuentos", description = "Controlador para gestionar y validar cupones de descuento")
public class DescuentoController {

    @Autowired
    private DescuentoService descuentoService;

    @GetMapping
    @Operation(summary = "Listar todos los descuentos", description = "Retorna una lista con todos los códigos de descuento guardados en la base de datos.")
    public List<Descuento> listar() {
        return descuentoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un descuento por ID")
    public Descuento obtener(@PathVariable Long id) {
        return descuentoService.obtenerPorId(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo código de descuento")
    public Descuento crear(@RequestBody DescuentoDto dto) {
    return descuentoService.guardar(dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un descuento por ID")
    public void eliminar(@PathVariable Long id) {
        descuentoService.eliminar(id);
    }

    @GetMapping("/validar/{codigo}")
    @Operation(summary = "Validar un código de descuento", description = "Verifica si el código existe, está activo lógicamente y se encuentra dentro del rango de fechas válido.")
    public boolean validarCodigo(
            @PathVariable String codigo) {

        return descuentoService.esValido(codigo);
    }

    @GetMapping("/codigo/{codigo}")
    @Operation(summary = "Obtener el objeto descuento completo por su código string")
    public Descuento obtenerPorCodigo(@PathVariable String codigo) {
        // Usamos el repositorio a través del método que ya tienes mapeado en tu service
        return descuentoService.obtenerPorCodigo(codigo); 
    }
}
