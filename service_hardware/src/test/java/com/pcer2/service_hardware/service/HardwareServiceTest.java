package com.pcer2.service_hardware.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.pcer2.service_hardware.model.Hardware;
import com.pcer2.service_hardware.repository.HardwareRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HardwareServiceTest {

    @Mock
    private HardwareRepository hardwareRepository;

    @InjectMocks
    private HardwareService hardwareService;

    @Test
    @DisplayName("Debe listar todas las piezas de hardware")
    void listarTodosTest() {

        Hardware h1 = new Hardware();
        Hardware h2 = new Hardware();

        when(hardwareRepository.findAll())
                .thenReturn(List.of(h1, h2));

        List<Hardware> resultado = hardwareService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        verify(hardwareRepository).findAll();
    }

    @Test
    @DisplayName("Debe buscar hardware por ID")
    void buscarPorIdTest() {

        Hardware hardware = new Hardware();
        hardware.setId(1L);

        when(hardwareRepository.findById(1L))
                .thenReturn(Optional.of(hardware));

        Optional<Hardware> resultado =
                hardwareService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());

        verify(hardwareRepository).findById(1L);
    }

    @Test
    @DisplayName("Debe retornar vacío cuando el hardware no existe")
    void buscarPorIdNoExisteTest() {

        when(hardwareRepository.findById(999L))
                .thenReturn(Optional.empty());

        Optional<Hardware> resultado =
                hardwareService.buscarPorId(999L);

        assertFalse(resultado.isPresent());

        verify(hardwareRepository).findById(999L);
    }

    @Test
    @DisplayName("Debe guardar hardware")
    void guardarTest() {

        Hardware hardware = new Hardware();
        hardware.setNombre("SSD Kingston");

        when(hardwareRepository.save(hardware))
                .thenReturn(hardware);

        Hardware resultado =
                hardwareService.guardar(hardware);

        assertNotNull(resultado);
        assertEquals("SSD Kingston", resultado.getNombre());

        verify(hardwareRepository).save(hardware);
    }

    @Test
    @DisplayName("Debe actualizar hardware existente")
    void actualizarTest() {

        Hardware existente = new Hardware();
        existente.setId(1L);
        existente.setNombre("SSD Antiguo");

        Hardware actualizado = new Hardware();
        actualizado.setNombre("SSD Nuevo");
        actualizado.setTipo("SSD");
        actualizado.setMarca("Kingston");
        actualizado.setCantidad(10);
        actualizado.setDetalles("NVMe Gen4");
        actualizado.setEstado("Disponible");
        actualizado.setPrecio(59990);

        when(hardwareRepository.findById(1L))
                .thenReturn(Optional.of(existente));

        when(hardwareRepository.save(any(Hardware.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Hardware resultado =
                hardwareService.actualizar(1L, actualizado);

        assertNotNull(resultado);
        assertEquals("SSD Nuevo", resultado.getNombre());
        assertEquals("SSD", resultado.getTipo());
        assertEquals("Kingston", resultado.getMarca());
        assertEquals(10, resultado.getCantidad());

        verify(hardwareRepository).findById(1L);
        verify(hardwareRepository).save(any(Hardware.class));
    }

    @Test
    @DisplayName("Debe retornar null al actualizar hardware inexistente")
    void actualizarNoExisteTest() {

        Hardware actualizado = new Hardware();

        when(hardwareRepository.findById(999L))
                .thenReturn(Optional.empty());

        Hardware resultado =
                hardwareService.actualizar(999L, actualizado);

        assertNull(resultado);

        verify(hardwareRepository).findById(999L);
        verify(hardwareRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe reducir cantidad cuando hay más de una unidad en inventario")
    void eliminarReduciendoCantidadTest() {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setCantidad(5);

        when(hardwareRepository.findById(1L))
                .thenReturn(Optional.of(hardware));

        when(hardwareRepository.save(any(Hardware.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        String resultado =
                hardwareService.eliminar(1L);

        assertEquals(
                "Cantidad reducida en 1. Nueva cantidad: 4",
                resultado);

        assertEquals(4, hardware.getCantidad());

        verify(hardwareRepository).save(hardware);
        verify(hardwareRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Debe eliminar hardware cuando queda una sola unidad en inventario")
    void eliminarHardwareTest() {

        Hardware hardware = new Hardware();
        hardware.setId(1L);
        hardware.setCantidad(1);

        when(hardwareRepository.findById(1L))
                .thenReturn(Optional.of(hardware));

        String resultado =
                hardwareService.eliminar(1L);

        assertEquals("Hardware eliminado.", resultado);

        verify(hardwareRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debe indicar cuando hardware no existe")
    void eliminarHardwareNoExisteTest() {

        when(hardwareRepository.findById(999L))
                .thenReturn(Optional.empty());

        String resultado =
                hardwareService.eliminar(999L);

        assertEquals("Hardware no encontrado.", resultado);

        verify(hardwareRepository).findById(999L);
        verify(hardwareRepository, never()).deleteById(anyLong());
    }
}