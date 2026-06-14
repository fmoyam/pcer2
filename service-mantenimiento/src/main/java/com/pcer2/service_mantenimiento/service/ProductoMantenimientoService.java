package com.pcer2.service_mantenimiento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcer2.service_mantenimiento.dto.ProductoMantenimientoDTO;
import com.pcer2.service_mantenimiento.model.ProductoMantenimiento;
import com.pcer2.service_mantenimiento.repository.ProductoMantenimientoRepository;

@Service
public class ProductoMantenimientoService {

    @Autowired
    private ProductoMantenimientoRepository productoMantenimientoRepository;

    public ProductoMantenimiento guardar(ProductoMantenimientoDTO productoMantenimientoDTO) {

        ProductoMantenimiento productoMantenimiento = new ProductoMantenimiento();

        productoMantenimiento.setNombre(productoMantenimientoDTO.getNombre());
        productoMantenimiento.setCategoria(productoMantenimientoDTO.getCategoria());
        productoMantenimiento.setDescripcion(productoMantenimientoDTO.getDescripcion());
        productoMantenimiento.setStockActual(productoMantenimientoDTO.getStockActual());
        productoMantenimiento.setPrecioUnitario(productoMantenimientoDTO.getPrecioUnitario());

        return productoMantenimientoRepository.save(productoMantenimiento);
    }

    public List<ProductoMantenimiento> listarTodos() {
        return productoMantenimientoRepository.findAll();
    }

    public Optional<ProductoMantenimiento> buscarPorId(Long id) {
        return productoMantenimientoRepository.findById(id);
    }

    public ProductoMantenimiento actualizarProducto(Long id, ProductoMantenimientoDTO productoMantenimientoDTO) {

        Optional<ProductoMantenimiento> productoExistente = productoMantenimientoRepository.findById(id);

        if (productoExistente.isPresent()) {

            ProductoMantenimiento productoMantenimiento = productoExistente.get();

            productoMantenimiento.setNombre(productoMantenimientoDTO.getNombre());
            productoMantenimiento.setCategoria(productoMantenimientoDTO.getCategoria());
            productoMantenimiento.setDescripcion(productoMantenimientoDTO.getDescripcion());
            productoMantenimiento.setStockActual(productoMantenimientoDTO.getStockActual());
            productoMantenimiento.setPrecioUnitario(productoMantenimientoDTO.getPrecioUnitario());

            return productoMantenimientoRepository.save(productoMantenimiento);
        }

        return null;
    }

    public void eliminarProducto(Long id) {
        productoMantenimientoRepository.deleteById(id);
    }
}