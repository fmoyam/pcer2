package com.pcer2.service_mantenimiento.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pcer2.service_mantenimiento.dto.ProductoMantenimientoDTO;
import com.pcer2.service_mantenimiento.model.ProductoMantenimiento;
import com.pcer2.service_mantenimiento.repository.ProductoMantenimientoRepository;

@ExtendWith(MockitoExtension.class)
public class ProductoMantenimientoServiceTest {

    @Mock
    private ProductoMantenimientoRepository productoMantenimientoRepository;

    @InjectMocks
    private ProductoMantenimientoService productoMantenimientoService;

    @Test
    void guardarProductoMantenimientoTest() {
        ProductoMantenimientoDTO productoDTO = new ProductoMantenimientoDTO();
        productoDTO.setNombre("Pasta térmica");
        productoDTO.setCategoria("Insumo técnico");
        productoDTO.setDescripcion("Pasta térmica para mantenimiento de CPU y GPU");
        productoDTO.setStockActual(25);
        productoDTO.setPrecioUnitario(4500.0);

        ProductoMantenimiento productoGuardado = new ProductoMantenimiento();
        productoGuardado.setId(1L);
        productoGuardado.setNombre("Pasta térmica");
        productoGuardado.setCategoria("Insumo técnico");
        productoGuardado.setDescripcion("Pasta térmica para mantenimiento de CPU y GPU");
        productoGuardado.setStockActual(25);
        productoGuardado.setPrecioUnitario(4500.0);

        when(productoMantenimientoRepository.save(any(ProductoMantenimiento.class))).thenReturn(productoGuardado);

        ProductoMantenimiento resultado = productoMantenimientoService.guardar(productoDTO);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Pasta térmica", resultado.getNombre());
        assertEquals("Insumo técnico", resultado.getCategoria());
        assertEquals("Pasta térmica para mantenimiento de CPU y GPU", resultado.getDescripcion());
        assertEquals(25, resultado.getStockActual());
        assertEquals(4500.0, resultado.getPrecioUnitario());

        verify(productoMantenimientoRepository, times(1)).save(any(ProductoMantenimiento.class));
    }

    @Test
    void listarProductosMantenimientoTest() {
        ProductoMantenimiento producto1 = new ProductoMantenimiento();
        producto1.setId(1L);
        producto1.setNombre("Pasta térmica");

        ProductoMantenimiento producto2 = new ProductoMantenimiento();
        producto2.setId(2L);
        producto2.setNombre("Alcohol isopropílico");

        List<ProductoMantenimiento> listaProductos = Arrays.asList(producto1, producto2);

        when(productoMantenimientoRepository.findAll()).thenReturn(listaProductos);

        List<ProductoMantenimiento> resultado = productoMantenimientoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Pasta térmica", resultado.get(0).getNombre());
        assertEquals("Alcohol isopropílico", resultado.get(1).getNombre());

        verify(productoMantenimientoRepository, times(1)).findAll();
    }

    @Test
    void buscarProductoMantenimientoPorIdTest() {
        ProductoMantenimiento producto = new ProductoMantenimiento();
        producto.setId(1L);
        producto.setNombre("Pasta térmica");
        producto.setCategoria("Insumo técnico");
        producto.setDescripcion("Pasta térmica para mantenimiento de CPU y GPU");
        producto.setStockActual(25);
        producto.setPrecioUnitario(4500.0);

        when(productoMantenimientoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<ProductoMantenimiento> resultado = productoMantenimientoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals("Pasta térmica", resultado.get().getNombre());

        verify(productoMantenimientoRepository, times(1)).findById(1L);
    }

    @Test
    void actualizarProductoMantenimientoTest() {
        ProductoMantenimiento productoExistente = new ProductoMantenimiento();
        productoExistente.setId(1L);
        productoExistente.setNombre("Pasta térmica");
        productoExistente.setCategoria("Insumo técnico");
        productoExistente.setDescripcion("Pasta térmica para mantenimiento de CPU y GPU");
        productoExistente.setStockActual(25);
        productoExistente.setPrecioUnitario(4500.0);

        ProductoMantenimientoDTO productoDTO = new ProductoMantenimientoDTO();
        productoDTO.setNombre("Pasta térmica premium");
        productoDTO.setCategoria("Insumo técnico");
        productoDTO.setDescripcion("Pasta térmica de alto rendimiento");
        productoDTO.setStockActual(30);
        productoDTO.setPrecioUnitario(6000.0);

        when(productoMantenimientoRepository.findById(1L)).thenReturn(Optional.of(productoExistente));
        when(productoMantenimientoRepository.save(any(ProductoMantenimiento.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductoMantenimiento resultado = productoMantenimientoService.actualizarProducto(1L, productoDTO);

        assertNotNull(resultado);
        assertEquals("Pasta térmica premium", resultado.getNombre());
        assertEquals("Insumo técnico", resultado.getCategoria());
        assertEquals("Pasta térmica de alto rendimiento", resultado.getDescripcion());
        assertEquals(30, resultado.getStockActual());
        assertEquals(6000.0, resultado.getPrecioUnitario());

        verify(productoMantenimientoRepository, times(1)).findById(1L);
        verify(productoMantenimientoRepository, times(1)).save(any(ProductoMantenimiento.class));
    }

    @Test
    void eliminarProductoMantenimientoTest() {
        doNothing().when(productoMantenimientoRepository).deleteById(1L);

        productoMantenimientoService.eliminarProducto(1L);

        verify(productoMantenimientoRepository, times(1)).deleteById(1L);
    }
}