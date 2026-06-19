package com.pcer2.service_equipo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import com.pcer2.service_equipo.model.Equipo;
import com.pcer2.service_equipo.repository.EquipoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoService equipoService;

    @Test
    @DisplayName("Debe listar todos los equipos")
    void listarTodosTest() {

        Equipo equipo1 = new Equipo();
        equipo1.setId(1L);
        equipo1.setMarca("HP");

        Equipo equipo2 = new Equipo();
        equipo2.setId(2L);
        equipo2.setMarca("Dell");

        when(equipoRepository.findAll())
                .thenReturn(Arrays.asList(equipo1, equipo2));

        List<Equipo> resultado = equipoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        verify(equipoRepository).findAll();
    }

    @Test
    @DisplayName("Debe buscar equipo por ID")
    void findByIdExistenteTest() {

        Equipo equipo = new Equipo();
        equipo.setId(1L);
        equipo.setMarca("HP");

        when(equipoRepository.findById(1L))
                .thenReturn(Optional.of(equipo));

        Optional<Equipo> resultado = equipoService.findById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("HP", resultado.get().getMarca());

        verify(equipoRepository).findById(1L);
    }

    @Test
    @DisplayName("Debe retornar vacío si no encuentra el equipo")
    void findByIdNoExistenteTest() {

        when(equipoRepository.findById(99L))
                .thenReturn(Optional.empty());

        Optional<Equipo> resultado = equipoService.findById(99L);

        assertFalse(resultado.isPresent());

        verify(equipoRepository).findById(99L);
    }

    @Test
    @DisplayName("Debe buscar equipo por número de serie")
    void buscarPorNumeroSerieTest() {

        Equipo equipo = new Equipo();
        equipo.setId(1L);
        equipo.setNumeroserie("ABC123");

        when(equipoRepository.findByNumeroserie("ABC123"))
                .thenReturn(Optional.of(equipo));

        Optional<Equipo> resultado =
                equipoService.buscarPorNumeroSerie("ABC123");

        assertTrue(resultado.isPresent());
        assertEquals("ABC123", resultado.get().getNumeroserie());

        verify(equipoRepository).findByNumeroserie("ABC123");
    }

    @Test
    @DisplayName("Debe buscar equipos por marca")
    void buscarPorMarcaTest() {

        Equipo equipo1 = new Equipo();
        equipo1.setMarca("Lenovo");

        Equipo equipo2 = new Equipo();
        equipo2.setMarca("Lenovo");

        when(equipoRepository.findByMarca("Lenovo"))
                .thenReturn(Arrays.asList(equipo1, equipo2));

        List<Equipo> resultado =
                equipoService.buscarPorMarca("Lenovo");

        assertEquals(2, resultado.size());

        verify(equipoRepository).findByMarca("Lenovo");
    }

    @Test
    @DisplayName("Debe guardar equipo")
    void guardarTest() {

        Equipo equipo = new Equipo();
        equipo.setMarca("Asus");
        equipo.setNumeroserie("XYZ999");

        when(equipoRepository.save(equipo))
                .thenReturn(equipo);

        Equipo resultado = equipoService.guardar(equipo);

        assertNotNull(resultado);
        assertEquals("Asus", resultado.getMarca());

        verify(equipoRepository).save(equipo);
    }

    @Test
    @DisplayName("Debe eliminar equipo existente")
    void eliminarExistenteTest() {

        when(equipoRepository.existsById(1L))
                .thenReturn(true);

        doNothing().when(equipoRepository)
                .deleteById(1L);

        equipoService.eliminar(1L);

        verify(equipoRepository).existsById(1L);
        verify(equipoRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar equipo inexistente")
    void eliminarNoExistenteTest() {

        when(equipoRepository.existsById(99L))
                .thenReturn(false);

        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        () -> equipoService.eliminar(99L));

        assertEquals(
                "Equipo no encontrado con ID: 99",
                exception.getMessage());

        verify(equipoRepository).existsById(99L);
        verify(equipoRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Debe verificar existencia por número de serie")
    void existePorNumeroSerieTest() {

        when(equipoRepository.existsByNumeroserie("ABC123"))
                .thenReturn(true);

        boolean resultado =
                equipoService.existePorNumeroserie("ABC123");

        assertTrue(resultado);

        verify(equipoRepository)
                .existsByNumeroserie("ABC123");
    }

    @Test
    @DisplayName("Debe obtener equipos por cliente")
    void findByClienteIdTest() {

        Equipo equipo1 = new Equipo();
        equipo1.setId(1L);

        Equipo equipo2 = new Equipo();
        equipo2.setId(2L);

        when(equipoRepository.findByClienteId(10L))
                .thenReturn(Arrays.asList(equipo1, equipo2));

        List<Equipo> resultado =
                equipoService.findByClienteId(10L);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        verify(equipoRepository).findByClienteId(10L);
    }

    @Test
    @DisplayName("Debe retornar lista vacía cuando cliente no tiene equipos")
    void findByClienteIdSinEquiposTest() {

        when(equipoRepository.findByClienteId(20L))
                .thenReturn(Collections.emptyList());

        List<Equipo> resultado =
                equipoService.findByClienteId(20L);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(equipoRepository).findByClienteId(20L);
    }
}
