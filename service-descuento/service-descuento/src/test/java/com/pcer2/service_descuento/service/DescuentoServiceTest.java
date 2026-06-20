package com.pcer2.service_descuento.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.pcer2.service_descuento.dto.DescuentoDto;
import com.pcer2.service_descuento.model.Descuento;
import com.pcer2.service_descuento.repository.DescuentoRepository;

@ExtendWith(MockitoExtension.class)
class DescuentoServiceTest {

    @Mock
    private DescuentoRepository descuentoRepository;

    @InjectMocks
    private DescuentoService descuentoService;

    @Test
    @DisplayName("Debe obtener todos los descuentos")
    void obtenerTodosTest() {

        Descuento d1 = new Descuento();
        d1.setId(1L);
        d1.setCodigo("PROMO10");

        Descuento d2 = new Descuento();
        d2.setId(2L);
        d2.setCodigo("PROMO20");

        when(descuentoRepository.findAll())
                .thenReturn(Arrays.asList(d1, d2));

        List<Descuento> descuentos = descuentoService.obtenerTodos();

        assertNotNull(descuentos);
        assertEquals(2, descuentos.size());

        verify(descuentoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe obtener descuento por ID")
    void obtenerPorIdTest() {

        Descuento descuento = new Descuento();
        descuento.setId(1L);
        descuento.setCodigo("PROMO10");

        when(descuentoRepository.findById(1L))
                .thenReturn(Optional.of(descuento));

        Descuento resultado = descuentoService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals("PROMO10", resultado.getCodigo());

        verify(descuentoRepository).findById(1L);
    }

    @Test
    @DisplayName("Debe guardar un descuento correctamente")
    void guardarTest() {

        DescuentoDto dto = new DescuentoDto();
        dto.setCodigo("PROMO2026");
        dto.setDescripcion("Descuento de bienvenida");
        dto.setPorcentajeDescuento(15);
        dto.setFechaInicio(LocalDate.now());
        dto.setFechaFin(LocalDate.now().plusDays(30));
        dto.setActivo(true);

        Descuento descuentoGuardado = new Descuento();
        descuentoGuardado.setId(1L);
        descuentoGuardado.setCodigo(dto.getCodigo());
        descuentoGuardado.setDescripcion(dto.getDescripcion());
        descuentoGuardado.setPorcentajeDescuento(dto.getPorcentajeDescuento());
        descuentoGuardado.setFechaInicio(dto.getFechaInicio());
        descuentoGuardado.setFechaFin(dto.getFechaFin());
        descuentoGuardado.setActivo(dto.getActivo());

        when(descuentoRepository.save(any(Descuento.class)))
                .thenReturn(descuentoGuardado);

        Descuento resultado = descuentoService.guardar(dto);

        assertNotNull(resultado);
        assertEquals("PROMO2026", resultado.getCodigo());
        assertEquals(15, resultado.getPorcentajeDescuento());
        assertTrue(resultado.getActivo());

        verify(descuentoRepository).save(any(Descuento.class));
    }

    @Test
    @DisplayName("Debe eliminar descuento por ID")
    void eliminarTest() {

        descuentoService.eliminar(1L);

        verify(descuentoRepository, times(1))
                .deleteById(1L);
    }

    @Test
    @DisplayName("Debe validar descuento activo y vigente")
    void esValidoTest() {

        Descuento descuento = new Descuento();
        descuento.setCodigo("PROMO2026");
        descuento.setActivo(true);
        descuento.setFechaInicio(LocalDate.now().minusDays(5));
        descuento.setFechaFin(LocalDate.now().plusDays(5));

        when(descuentoRepository.findByCodigo("PROMO2026"))
                .thenReturn(Optional.of(descuento));

        boolean resultado = descuentoService.esValido("PROMO2026");

        assertTrue(resultado);

        verify(descuentoRepository).findByCodigo("PROMO2026");
    }

    @Test
    @DisplayName("Debe retornar false si el descuento está vencido")
    void esValidoDescuentoVencidoTest() {

        Descuento descuento = new Descuento();
        descuento.setCodigo("PROMO2026");
        descuento.setActivo(true);
        descuento.setFechaInicio(LocalDate.now().minusDays(20));
        descuento.setFechaFin(LocalDate.now().minusDays(1));

        when(descuentoRepository.findByCodigo("PROMO2026"))
                .thenReturn(Optional.of(descuento));

        boolean resultado = descuentoService.esValido("PROMO2026");

        assertFalse(resultado);
    }

    @Test
    @DisplayName("Debe retornar false si el descuento está inactivo")
    void esValidoDescuentoInactivoTest() {

        Descuento descuento = new Descuento();
        descuento.setCodigo("PROMO2026");
        descuento.setActivo(false);
        descuento.setFechaInicio(LocalDate.now().minusDays(5));
        descuento.setFechaFin(LocalDate.now().plusDays(5));

        when(descuentoRepository.findByCodigo("PROMO2026"))
                .thenReturn(Optional.of(descuento));

        boolean resultado = descuentoService.esValido("PROMO2026");

        assertFalse(resultado);
    }

    @Test
    @DisplayName("Debe retornar false cuando el código no existe")
    void esValidoCodigoNoExisteTest() {

        when(descuentoRepository.findByCodigo("NO_EXISTE"))
                .thenReturn(Optional.empty());

        boolean resultado = descuentoService.esValido("NO_EXISTE");

        assertFalse(resultado);
    }

    @Test
    @DisplayName("Debe obtener descuento por código")
    void obtenerPorCodigoTest() {

        Descuento descuento = new Descuento();
        descuento.setId(1L);
        descuento.setCodigo("PROMO2026");

        when(descuentoRepository.findByCodigo("PROMO2026"))
                .thenReturn(Optional.of(descuento));

        Descuento resultado = descuentoService.obtenerPorCodigo("PROMO2026");

        assertNotNull(resultado);
        assertEquals("PROMO2026", resultado.getCodigo());

        verify(descuentoRepository).findByCodigo("PROMO2026");
    }

    @Test
    @DisplayName("Debe retornar null cuando el código no existe")
    void obtenerPorCodigoNoExisteTest() {

        when(descuentoRepository.findByCodigo("NO_EXISTE"))
                .thenReturn(Optional.empty());

        Descuento resultado = descuentoService.obtenerPorCodigo("NO_EXISTE");

        assertNull(resultado);
    }
}