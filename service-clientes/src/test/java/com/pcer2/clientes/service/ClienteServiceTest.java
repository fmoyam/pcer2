package com.pcer2.clientes.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import com.pcer2.clientes.dto.EquipoDTO;
import com.pcer2.clientes.model.Cliente;
import com.pcer2.clientes.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EquipoClienteService equipoClientService;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    @DisplayName("Debe listar todos los clientes con sus equipos")
    void listarTodosTest() {

        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNombre("Juan Perez");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNombre("Maria Soto");

        EquipoDTO equipo = new EquipoDTO();
        equipo.setId(100L);

        when(clienteRepository.findAll())
                .thenReturn(Arrays.asList(cliente1, cliente2));

        when(equipoClientService.getEquiposByClienteId(1L))
                .thenReturn(List.of(equipo));

        when(equipoClientService.getEquiposByClienteId(2L))
                .thenReturn(Collections.emptyList());

        List<Cliente> resultado = clienteService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        assertEquals(1, resultado.get(0).getEquipos().size());
        assertEquals(0, resultado.get(1).getEquipos().size());

        verify(clienteRepository).findAll();
        verify(equipoClientService).getEquiposByClienteId(1L);
        verify(equipoClientService).getEquiposByClienteId(2L);
    }

    @Test
    @DisplayName("Debe buscar cliente por ID y cargar equipos")
    void buscarPorIdExistenteTest() {

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan Perez");

        EquipoDTO equipo = new EquipoDTO();
        equipo.setId(100L);

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        when(equipoClientService.getEquiposByClienteId(1L))
                .thenReturn(List.of(equipo));

        Optional<Cliente> resultado = clienteService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Juan Perez", resultado.get().getNombre());
        assertEquals(1, resultado.get().getEquipos().size());

        verify(clienteRepository).findById(1L);
        verify(equipoClientService).getEquiposByClienteId(1L);
    }

    @Test
    @DisplayName("Debe retornar vacío cuando cliente no existe")
    void buscarPorIdNoExistenteTest() {

        when(clienteRepository.findById(99L))
                .thenReturn(Optional.empty());

        Optional<Cliente> resultado = clienteService.buscarPorId(99L);

        assertFalse(resultado.isPresent());

        verify(clienteRepository).findById(99L);
        verifyNoInteractions(equipoClientService);
    }

    @Test
    @DisplayName("Debe guardar cliente usando save")
    void saveTest() {

        Cliente cliente = new Cliente();
        cliente.setNombre("Pedro Diaz");

        when(clienteRepository.save(cliente))
                .thenReturn(cliente);

        Cliente resultado = clienteService.save(cliente);

        assertNotNull(resultado);
        assertEquals("Pedro Diaz", resultado.getNombre());

        verify(clienteRepository).save(cliente);
    }

    @Test
    @DisplayName("Debe guardar cliente usando guardar")
    void guardarTest() {

        Cliente cliente = new Cliente();
        cliente.setNombre("Ana Torres");

        when(clienteRepository.save(cliente))
                .thenReturn(cliente);

        Cliente resultado = clienteService.guardar(cliente);

        assertNotNull(resultado);
        assertEquals("Ana Torres", resultado.getNombre());

        verify(clienteRepository).save(cliente);
    }

    @Test
    @DisplayName("Debe buscar cliente por rut")
    void buscarPorRutTest() {

        Cliente cliente = new Cliente();
        cliente.setRut("11111111-1");

        when(clienteRepository.findByRut("11111111-1"))
                .thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado =
                clienteService.buscarPorRut("11111111-1");

        assertTrue(resultado.isPresent());
        assertEquals("11111111-1", resultado.get().getRut());

        verify(clienteRepository).findByRut("11111111-1");
    }

    @Test
    @DisplayName("Debe eliminar cliente")
    void eliminarClienteTest() {

        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.eliminarCliente(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }
}