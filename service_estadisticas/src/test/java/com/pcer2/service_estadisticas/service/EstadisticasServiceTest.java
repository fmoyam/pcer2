package com.pcer2.service_estadisticas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import com.pcer2.service_estadisticas.dto.*;
import com.pcer2.service_estadisticas.model.Estadistica;
import com.pcer2.service_estadisticas.repository.EstadisticaRepository;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class EstadisticasServiceTest {

    @Mock
    private EstadisticaRepository repository;

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

    @InjectMocks
    private EstadisticasService service;

    @Test
    @DisplayName("Debe generar reporte de clientes")
    void generarReporteClientesTest() throws Exception {

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

        ClienteDTO cliente1 = new ClienteDTO();
        cliente1.setId(1L);
        cliente1.setNombre("Juan");
        cliente1.setApellido("Perez");
        cliente1.setRut("11111111-1");
        cliente1.setFecha_registro("2020-01-01");
        cliente1.setOrdenes_totales(10);
        cliente1.setEquipos(List.of(new EquipoDTO(), new EquipoDTO()));

        ClienteDTO cliente2 = new ClienteDTO();
        cliente2.setId(2L);
        cliente2.setNombre("Pedro");
        cliente2.setApellido("Gomez");
        cliente2.setRut("22222222-2");
        cliente2.setFecha_registro("2023-01-01");
        cliente2.setOrdenes_totales(5);
        cliente2.setEquipos(List.of(new EquipoDTO()));

        when(responseSpec.bodyToMono(ClienteDTO[].class))
                .thenReturn(Mono.just(new ClienteDTO[]{
                        cliente1,
                        cliente2
                }));

        when(repository.save(any(Estadistica.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Estadistica resultado = service.generarReporteClientes();

        assertNotNull(resultado);
        assertEquals("CLIENTES", resultado.getCategoria());
        assertNotNull(resultado.getReporteJson());

        verify(repository).save(any(Estadistica.class));
    }

    @Test
    @DisplayName("Debe generar reporte de equipos")
    void generarReporteEquiposTest() throws Exception {

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

        EquipoDTO equipo1 = new EquipoDTO();
        equipo1.setId(1L);
        equipo1.setTipoEquipo("Notebook");
        equipo1.setMarca("HP");
        equipo1.setTipoAlmacen("SSD");
        equipo1.setVeces_reparado(3);

        EquipoDTO equipo2 = new EquipoDTO();
        equipo2.setId(2L);
        equipo2.setTipoEquipo("Notebook");
        equipo2.setMarca("HP");
        equipo2.setTipoAlmacen("SSD");
        equipo2.setVeces_reparado(8);

        when(responseSpec.bodyToMono(EquipoDTO[].class))
                .thenReturn(Mono.just(new EquipoDTO[]{
                        equipo1,
                        equipo2
                }));

        when(repository.save(any(Estadistica.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Estadistica resultado = service.generarReporteEquipos();

        assertNotNull(resultado);
        assertEquals("EQUIPOS", resultado.getCategoria());
        assertNotNull(resultado.getReporteJson());

        verify(repository).save(any(Estadistica.class));
    }

    @Test
    @DisplayName("Debe generar reporte de vouchers")
    void generarReporteVoucherTest() throws Exception {

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

        VoucherDTO voucher1 = new VoucherDTO();
        voucher1.setId(1L);
        voucher1.setMetodoPago("EFECTIVO");
        voucher1.setTotal(10000.0);

        VoucherDTO voucher2 = new VoucherDTO();
        voucher2.setId(2L);
        voucher2.setMetodoPago("EFECTIVO");
        voucher2.setTotal(20000.0);

        when(responseSpec.bodyToMono(VoucherDTO[].class))
                .thenReturn(Mono.just(new VoucherDTO[]{
                        voucher1,
                        voucher2
                }));

        when(repository.save(any(Estadistica.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Estadistica resultado = service.generarReporteVoucher();

        assertNotNull(resultado);
        assertEquals("VOUCHERS", resultado.getCategoria());
        assertNotNull(resultado.getReporteJson());

        verify(repository).save(any(Estadistica.class));
    }

    @Test
    @DisplayName("Debe listar estadísticas")
    void listarTest() {

        Estadistica e1 = new Estadistica();
        Estadistica e2 = new Estadistica();

        when(repository.findAll())
                .thenReturn(List.of(e1, e2));

        List<Estadistica> resultado = service.listar();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Debe buscar estadística por ID")
    void buscarTest() {

        Estadistica estadistica = new Estadistica();
        estadistica.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(estadistica));

        Optional<Estadistica> resultado = service.buscar(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());

        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Debe retornar vacío cuando la estadística no existe")
    void buscarNoExisteTest() {

        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        Optional<Estadistica> resultado = service.buscar(999L);

        assertFalse(resultado.isPresent());

        verify(repository).findById(999L);
    }

    @Test
    @DisplayName("Debe eliminar una estadística")
    void eliminarTest() {

        doNothing().when(repository).deleteById(1L);

        service.eliminar(1L);

        verify(repository).deleteById(1L);
    }
}