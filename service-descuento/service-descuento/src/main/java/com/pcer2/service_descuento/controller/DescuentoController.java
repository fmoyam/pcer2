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

@RestController
@RequestMapping("/api/v1/descuento")
public class DescuentoController {

    @Autowired
    private DescuentoService descuentoService;

    @GetMapping
    public List<Descuento> listar() {
        return descuentoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Descuento obtener(@PathVariable Long id) {
        return descuentoService.obtenerPorId(id);
    }

    @PostMapping
    public Descuento crear(@RequestBody DescuentoDto dto) {
    return descuentoService.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        descuentoService.eliminar(id);
    }

    @GetMapping("/validar/{codigo}")
    public boolean validarCodigo(
            @PathVariable String codigo) {

        return descuentoService.esValido(codigo);
    }

}
