package com.pcer2.service_orden.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.pcer2.service_orden.dto.OrdenTrabajoDTO;
import com.pcer2.service_orden.model.OrdenTrabajo;
import com.pcer2.service_orden.repository.OrdenTrabajoRepository;

import reactor.core.publisher.Mono;


// Activamos Mockito para poder usar @Mock e @InjectMocks
@ExtendWith(MockitoExtension.class)
public class OrdenTrabajoServiceTest {

    @Mock  // Simula el Repository para no usar la base de datos real
    private OrdenTrabajoRepository ordenTrabajoRepository;

    @Mock // Simula WebClient para no llamar a otros microservicios reales
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks // Crea el Service real e inserta dentro los mocks anteriores
    private OrdenTrabajoService ordenTrabajoService;

    @Test
    void guardarOrdenTrabajoTest() {
        OrdenTrabajoDTO ordenDTO = new OrdenTrabajoDTO();
        ordenDTO.setClienteId(1L);
        ordenDTO.setEquipoId(1L);
        ordenDTO.setServicioId(1L);
        ordenDTO.setSoftwareId(1L);
        ordenDTO.setProductoMantenimientoId(1L);
        ordenDTO.setDescripcionProblema("Equipo requiere mantenimiento completo e instalación de software");
        ordenDTO.setFechaIngreso(LocalDate.of(2026, 6, 14));
        ordenDTO.setFechaEntregaEstimada(LocalDate.of(2026, 6, 17));
        ordenDTO.setEstado("Ingresada");
        ordenDTO.setPrecioTotal(39500.0);

        OrdenTrabajo ordenGuardada = new OrdenTrabajo();
        ordenGuardada.setId(1L);
        ordenGuardada.setClienteId(1L);
        ordenGuardada.setEquipoId(1L);
        ordenGuardada.setServicioId(1L);
        ordenGuardada.setSoftwareId(1L);
        ordenGuardada.setProductoMantenimientoId(1L);
        ordenGuardada.setDescripcionProblema("Equipo requiere mantenimiento completo e instalación de software");
        ordenGuardada.setFechaIngreso(LocalDate.of(2026, 6, 14));
        ordenGuardada.setFechaEntregaEstimada(LocalDate.of(2026, 6, 17));
        ordenGuardada.setEstado("Ingresada");
        ordenGuardada.setPrecioTotal(39500.0);

        when(ordenTrabajoRepository.save(any(OrdenTrabajo.class))).thenReturn(ordenGuardada);

        OrdenTrabajo resultado = ordenTrabajoService.guardar(ordenDTO);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(1L, resultado.getClienteId());
        assertEquals(1L, resultado.getEquipoId());
        assertEquals(1L, resultado.getServicioId());
        assertEquals(1L, resultado.getSoftwareId());
        assertEquals(1L, resultado.getProductoMantenimientoId());
        assertEquals("Ingresada", resultado.getEstado());
        assertEquals(39500.0, resultado.getPrecioTotal());

        verify(ordenTrabajoRepository, times(1)).save(any(OrdenTrabajo.class));
    }

    @Test
    void listarOrdenesTrabajoTest() {
        OrdenTrabajo orden1 = new OrdenTrabajo();
        orden1.setId(1L);
        orden1.setEstado("Ingresada");

        OrdenTrabajo orden2 = new OrdenTrabajo();
        orden2.setId(2L);
        orden2.setEstado("Finalizada");

        List<OrdenTrabajo> listaOrdenes = Arrays.asList(orden1, orden2);

        when(ordenTrabajoRepository.findAll()).thenReturn(listaOrdenes);

        List<OrdenTrabajo> resultado = ordenTrabajoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Ingresada", resultado.get(0).getEstado());
        assertEquals("Finalizada", resultado.get(1).getEstado());

        verify(ordenTrabajoRepository, times(1)).findAll();
    }

    @Test
    void buscarOrdenTrabajoPorIdTest() {
        OrdenTrabajo orden = new OrdenTrabajo();
        orden.setId(1L);
        orden.setDescripcionProblema("Equipo requiere diagnóstico");
        orden.setEstado("Ingresada");

        /*
         * Dejamos los IDs externos en null para que este test no llame a WebClient.
         * Así probamos la búsqueda de la orden sin depender de otros microservicios.
         */

        when(ordenTrabajoRepository.findById(1L)).thenReturn(Optional.of(orden));

        Optional<OrdenTrabajo> resultado = ordenTrabajoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals("Ingresada", resultado.get().getEstado());

        verify(ordenTrabajoRepository, times(1)).findById(1L);
    }

    @Test
    void buscarOrdenTrabajoConDatosSoftwareWebClientTest() {
        OrdenTrabajo orden = new OrdenTrabajo();
        orden.setId(1L);
        orden.setSoftwareId(1L);
        orden.setDescripcionProblema("Equipo requiere instalación de software");
        orden.setEstado("Ingresada");

        Map<String, Object> datosSoftware = new HashMap<>();
        datosSoftware.put("id", 1L);
        datosSoftware.put("nombre", "Windows 11 Pro");
        datosSoftware.put("marca", "Microsoft");

        when(ordenTrabajoRepository.findById(1L)).thenReturn(Optional.of(orden));

        when(webClientBuilder.build()).thenReturn(webClient);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Object.class)).thenReturn(Mono.just(datosSoftware));

        Optional<OrdenTrabajo> resultado = ordenTrabajoService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertNotNull(resultado.get().getDatosSoftware());
        assertEquals(datosSoftware, resultado.get().getDatosSoftware());

        verify(ordenTrabajoRepository, times(1)).findById(1L);
        verify(webClientBuilder, times(1)).build();
    }

    @Test
    void actualizarOrdenTrabajoTest() {
        OrdenTrabajo ordenExistente = new OrdenTrabajo();
        ordenExistente.setId(1L);
        ordenExistente.setClienteId(1L);
        ordenExistente.setEquipoId(1L);
        ordenExistente.setServicioId(1L);
        ordenExistente.setSoftwareId(1L);
        ordenExistente.setProductoMantenimientoId(1L);
        ordenExistente.setDescripcionProblema("Equipo requiere diagnóstico");
        ordenExistente.setFechaIngreso(LocalDate.of(2026, 6, 14));
        ordenExistente.setFechaEntregaEstimada(LocalDate.of(2026, 6, 17));
        ordenExistente.setEstado("Ingresada");
        ordenExistente.setPrecioTotal(30000.0);

        OrdenTrabajoDTO ordenDTO = new OrdenTrabajoDTO();
        ordenDTO.setClienteId(1L);
        ordenDTO.setEquipoId(1L);
        ordenDTO.setServicioId(1L);
        ordenDTO.setSoftwareId(1L);
        ordenDTO.setProductoMantenimientoId(1L);
        ordenDTO.setDescripcionProblema("Equipo requiere mantenimiento completo");
        ordenDTO.setFechaIngreso(LocalDate.of(2026, 6, 14));
        ordenDTO.setFechaEntregaEstimada(LocalDate.of(2026, 6, 18));
        ordenDTO.setEstado("En proceso");
        ordenDTO.setPrecioTotal(39500.0);

        when(ordenTrabajoRepository.findById(1L)).thenReturn(Optional.of(ordenExistente));
        when(ordenTrabajoRepository.save(any(OrdenTrabajo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OrdenTrabajo resultado = ordenTrabajoService.actualizarOrden(1L, ordenDTO);

        assertNotNull(resultado);
        assertEquals("Equipo requiere mantenimiento completo", resultado.getDescripcionProblema());
        assertEquals("En proceso", resultado.getEstado());
        assertEquals(39500.0, resultado.getPrecioTotal());

        verify(ordenTrabajoRepository, times(1)).findById(1L);
        verify(ordenTrabajoRepository, times(1)).save(any(OrdenTrabajo.class));
    }

    @Test
    void eliminarOrdenTrabajoTest() {
        doNothing().when(ordenTrabajoRepository).deleteById(1L);

        ordenTrabajoService.eliminarOrden(1L);

        verify(ordenTrabajoRepository, times(1)).deleteById(1L);
    }
}