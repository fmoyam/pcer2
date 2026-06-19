package com.pcer2.service_servicio.service;

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

import com.pcer2.service_servicio.dto.ServicioDTO;
import com.pcer2.service_servicio.model.Servicio;
import com.pcer2.service_servicio.repository.ServicioRepository;

@ExtendWith(MockitoExtension.class)
public class ServicioServiceTest {

    @Mock
    private ServicioRepository servicioRepository;

    @InjectMocks
    private ServicioService servicioService;

    @Test
    void guardarServicioTest() {
        ServicioDTO servicioDTO = new ServicioDTO();
        servicioDTO.setNombre("Mantención completa de equipo");
        servicioDTO.setDescripcion("Limpieza completa del hardware del equipo");
        servicioDTO.setPrecioBase(35000.0);
        servicioDTO.setActivo(true);

        Servicio servicioGuardado = new Servicio();
        servicioGuardado.setId(1L);
        servicioGuardado.setNombre("Mantención completa de equipo");
        servicioGuardado.setDescripcion("Limpieza completa del hardware del equipo");
        servicioGuardado.setPrecioBase(35000.0);
        servicioGuardado.setActivo(true);

        when(servicioRepository.save(any(Servicio.class))).thenReturn(servicioGuardado);

        Servicio resultado = servicioService.guardar(servicioDTO);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Mantención completa de equipo", resultado.getNombre());
        assertEquals("Limpieza completa del hardware del equipo", resultado.getDescripcion());
        assertEquals(35000.0, resultado.getPrecioBase());
        assertEquals(true, resultado.getActivo());

        verify(servicioRepository, times(1)).save(any(Servicio.class));
    }

    @Test
    void listarServiciosTest() {
        Servicio servicio1 = new Servicio();
        servicio1.setId(1L);
        servicio1.setNombre("Diagnóstico técnico");

        Servicio servicio2 = new Servicio();
        servicio2.setId(2L);
        servicio2.setNombre("Mantención completa");

        List<Servicio> listaServicios = Arrays.asList(servicio1, servicio2);

        when(servicioRepository.findAll()).thenReturn(listaServicios);

        List<Servicio> resultado = servicioService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Diagnóstico técnico", resultado.get(0).getNombre());
        assertEquals("Mantención completa", resultado.get(1).getNombre());

        verify(servicioRepository, times(1)).findAll();
    }

    @Test
    void buscarServicioPorIdTest() {
        Servicio servicio = new Servicio();
        servicio.setId(1L);
        servicio.setNombre("Diagnóstico técnico");
        servicio.setDescripcion("Revisión general del computador");
        servicio.setPrecioBase(15000.0);
        servicio.setActivo(true);

        when(servicioRepository.findById(1L)).thenReturn(Optional.of(servicio));

        Optional<Servicio> resultado = servicioService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals("Diagnóstico técnico", resultado.get().getNombre());

        verify(servicioRepository, times(1)).findById(1L);
    }

    @Test
    void actualizarServicioTest() {
        Servicio servicioExistente = new Servicio();
        servicioExistente.setId(1L);
        servicioExistente.setNombre("Diagnóstico técnico");
        servicioExistente.setDescripcion("Revisión general del computador");
        servicioExistente.setPrecioBase(15000.0);
        servicioExistente.setActivo(true);

        ServicioDTO servicioDTO = new ServicioDTO();
        servicioDTO.setNombre("Diagnóstico técnico avanzado");
        servicioDTO.setDescripcion("Revisión completa de hardware y software");
        servicioDTO.setPrecioBase(20000.0);
        servicioDTO.setActivo(true);

        when(servicioRepository.findById(1L)).thenReturn(Optional.of(servicioExistente));
        when(servicioRepository.save(any(Servicio.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Servicio resultado = servicioService.actualizarServicio(1L, servicioDTO);

        assertNotNull(resultado);
        assertEquals("Diagnóstico técnico avanzado", resultado.getNombre());
        assertEquals("Revisión completa de hardware y software", resultado.getDescripcion());
        assertEquals(20000.0, resultado.getPrecioBase());
        assertEquals(true, resultado.getActivo());

        verify(servicioRepository, times(1)).findById(1L);
        verify(servicioRepository, times(1)).save(any(Servicio.class));
    }

    @Test
    void eliminarServicioTest() {
        doNothing().when(servicioRepository).deleteById(1L);

        servicioService.eliminarServicio(1L);

        verify(servicioRepository, times(1)).deleteById(1L);
    }
}