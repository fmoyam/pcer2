package com.pcer2.service_voucher.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import com.pcer2.service_voucher.dto.VoucherDTO;
import com.pcer2.service_voucher.model.Voucher;
import com.pcer2.service_voucher.repository.VoucherRepository;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class VoucherServiceTest {

    @Mock
    private VoucherRepository voucherRepository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private VoucherService voucherService;

    private Voucher voucher;
    private VoucherDTO dto;

    @BeforeEach
    void setUp() {

        voucher = new Voucher();
        voucher.setId(1L);
        voucher.setOrdenId(100L);
        voucher.setFechaEmision(LocalDate.of(2026, 1, 15));
        voucher.setMetodoPago("Transferencia");
        voucher.setTotal(34990.0);
        voucher.setCantidadServicios(2);
        voucher.setEstado("Finalizado");
        voucher.setObservacion("Cliente conforme");
        voucher.setCodigoDescuento("DIADELPADRE2026");

        dto = new VoucherDTO();
        dto.setOrdenId(100L);
        dto.setFechaEmision(LocalDate.of(2026, 1, 15));
        dto.setMetodoPago("Transferencia");
        dto.setTotal(34990.0);
        dto.setCantidadServicios(2);
        dto.setEstado("Finalizado");
        dto.setObservacion("Cliente conforme");
        dto.setCodigoDescuento("DIADELPADRE2026");
    }

    @Test
    void guardarVoucherTest() {

        when(voucherRepository.save(any(Voucher.class)))
                .thenReturn(voucher);

        Voucher resultado = voucherService.guardar(dto);

        assertNotNull(resultado);
        assertEquals(100L, resultado.getOrdenId());
        assertEquals("Transferencia", resultado.getMetodoPago());
        assertEquals(34990.0, resultado.getTotal());
        assertEquals("DIADELPADRE2026", resultado.getCodigoDescuento());

        verify(voucherRepository, times(1))
                .save(any(Voucher.class));
    }

    @Test
    void obtenerTodosConDatosExternosTest() {

        when(voucherRepository.findAll())
                .thenReturn(List.of(voucher));

        when(webClient.get())
                .thenReturn(requestHeadersUriSpec);

        when(requestHeadersUriSpec.uri(anyString()))
                .thenReturn(requestHeadersSpec);

        when(requestHeadersSpec.retrieve())
                .thenReturn(responseSpec);

        when(responseSpec.bodyToMono(Object.class))
                .thenReturn(Mono.just("DATOS_EXTERNOS"));

        List<Voucher> resultado = voucherService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        Voucher obtenido = resultado.get(0);

        assertNotNull(obtenido.getDatosOrden());
        assertNotNull(obtenido.getDatosDescuento());

        verify(voucherRepository, times(1))
                .findAll();

        verify(webClient, times(2))
                .get();
    }

    @Test
    void obtenerTodosCuandoFallanServiciosExternosTest() {

        when(voucherRepository.findAll())
                .thenReturn(List.of(voucher));

        when(webClient.get())
                .thenThrow(new RuntimeException("Servicio caído"));

        List<Voucher> resultado = voucherService.obtenerTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        Voucher obtenido = resultado.get(0);

        assertNull(obtenido.getDatosOrden());
        assertNull(obtenido.getDatosDescuento());

        verify(voucherRepository, times(1))
                .findAll();
    }

    @Test
    void obtenerPorIdConDatosExternosTest() {

        when(voucherRepository.findById(1L))
                .thenReturn(Optional.of(voucher));

        when(webClient.get())
                .thenReturn(requestHeadersUriSpec);

        when(requestHeadersUriSpec.uri(anyString()))
                .thenReturn(requestHeadersSpec);

        when(requestHeadersSpec.retrieve())
                .thenReturn(responseSpec);

        when(responseSpec.bodyToMono(Object.class))
                .thenReturn(Mono.just("DATOS_EXTERNOS"));

        Optional<Voucher> resultado =
                voucherService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());

        Voucher obtenido = resultado.get();

        assertEquals(1L, obtenido.getId());
        assertNotNull(obtenido.getDatosOrden());
        assertNotNull(obtenido.getDatosDescuento());

        verify(voucherRepository, times(1))
                .findById(1L);

        verify(webClient, times(2))
                .get();
    }

    @Test
    void obtenerPorIdCuandoFallaServicioExternoTest() {

        when(voucherRepository.findById(1L))
                .thenReturn(Optional.of(voucher));

        when(webClient.get())
                .thenThrow(new RuntimeException("Servicio caído"));

        Optional<Voucher> resultado =
                voucherService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());

        Voucher obtenido = resultado.get();

        assertNull(obtenido.getDatosOrden());
        assertNull(obtenido.getDatosDescuento());

        verify(voucherRepository, times(1))
                .findById(1L);
    }

    @Test
    void obtenerPorIdNoExisteTest() {

        when(voucherRepository.findById(1L))
                .thenReturn(Optional.empty());

        Optional<Voucher> resultado =
                voucherService.obtenerPorId(1L);

        assertFalse(resultado.isPresent());

        verify(voucherRepository, times(1))
                .findById(1L);

        verifyNoInteractions(webClient);
    }

    @Test
    void actualizarVoucherTest() {

        when(voucherRepository.findById(1L))
                .thenReturn(Optional.of(voucher));

        when(voucherRepository.save(any(Voucher.class)))
                .thenReturn(voucher);

        Voucher resultado =
                voucherService.actualizar(1L, dto);

        assertNotNull(resultado);
        assertEquals("Transferencia",
                resultado.getMetodoPago());
        assertEquals("Finalizado",
                resultado.getEstado());

        verify(voucherRepository, times(1))
                .findById(1L);

        verify(voucherRepository, times(1))
                .save(any(Voucher.class));
    }

    @Test
    void actualizarVoucherNoEncontradoTest() {

        when(voucherRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(RuntimeException.class,
                        () -> voucherService.actualizar(1L, dto));

        assertEquals("Voucher no encontrado",
                exception.getMessage());

        verify(voucherRepository, times(1))
                .findById(1L);
    }

    @Test
    void eliminarVoucherTest() {

        doNothing().when(voucherRepository)
                .deleteById(1L);

        voucherService.eliminar(1L);

        verify(voucherRepository, times(1))
                .deleteById(1L);
    }
}
