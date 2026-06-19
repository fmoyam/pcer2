package com.pcer2.service_software.service;

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

import com.pcer2.service_software.dto.LicenciaSoftwareDTO;
import com.pcer2.service_software.model.LicenciaSoftware;
import com.pcer2.service_software.repository.LicenciaSoftwareRepository;

@ExtendWith(MockitoExtension.class)
public class LicenciaSoftwareServiceTest {

    @Mock //Simula el Repository. No usamos base de datos real.
    private LicenciaSoftwareRepository licenciaSoftwareRepository;

    @InjectMocks //Crea el Service y le inyecta el Repository falso.
    private LicenciaSoftwareService licenciaSoftwareService;

    @Test
    void guardarLicenciaSoftwareTest() {
        LicenciaSoftwareDTO licenciaDTO = new LicenciaSoftwareDTO();
        licenciaDTO.setNombre("Windows 11 Pro");
        licenciaDTO.setMarca("Microsoft");
        licenciaDTO.setVersion("11 Pro");
        licenciaDTO.setSerial("WIN11-PRO-ABC123");

        LicenciaSoftware licenciaGuardada = new LicenciaSoftware();
        licenciaGuardada.setId(1L);
        licenciaGuardada.setNombre("Windows 11 Pro");
        licenciaGuardada.setMarca("Microsoft");
        licenciaGuardada.setVersion("11 Pro");
        licenciaGuardada.setSerial("WIN11-PRO-ABC123");

        when(licenciaSoftwareRepository.save(any(LicenciaSoftware.class))).thenReturn(licenciaGuardada);
        //Le decimos a Mockito qué debe responder el Repository falso.

        LicenciaSoftware resultado = licenciaSoftwareService.guardar(licenciaDTO);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Windows 11 Pro", resultado.getNombre());
        assertEquals("Microsoft", resultado.getMarca());
        assertEquals("11 Pro", resultado.getVersion());
        assertEquals("WIN11-PRO-ABC123", resultado.getSerial());

        verify(licenciaSoftwareRepository, times(1)).save(any(LicenciaSoftware.class));
        //Verifica que el método del Repository realmente fue llamado.
    }

    @Test
    void listarLicenciasSoftwareTest() {
        LicenciaSoftware licencia1 = new LicenciaSoftware();
        licencia1.setId(1L);
        licencia1.setNombre("Windows 11 Pro");

        LicenciaSoftware licencia2 = new LicenciaSoftware();
        licencia2.setId(2L);
        licencia2.setNombre("Office 2021");

        List<LicenciaSoftware> listaLicencias = Arrays.asList(licencia1, licencia2);

        when(licenciaSoftwareRepository.findAll()).thenReturn(listaLicencias);

        List<LicenciaSoftware> resultado = licenciaSoftwareService.listarTodos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Windows 11 Pro", resultado.get(0).getNombre());
        assertEquals("Office 2021", resultado.get(1).getNombre());

        verify(licenciaSoftwareRepository, times(1)).findAll();
    }

    @Test
    void buscarLicenciaSoftwarePorIdTest() {
        LicenciaSoftware licencia = new LicenciaSoftware();
        licencia.setId(1L);
        licencia.setNombre("Windows 11 Pro");
        licencia.setMarca("Microsoft");
        licencia.setVersion("11 Pro");
        licencia.setSerial("WIN11-PRO-ABC123");

        when(licenciaSoftwareRepository.findById(1L)).thenReturn(Optional.of(licencia));

        Optional<LicenciaSoftware> resultado = licenciaSoftwareService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        assertEquals("Windows 11 Pro", resultado.get().getNombre());

        verify(licenciaSoftwareRepository, times(1)).findById(1L);
    }

    @Test
    void actualizarLicenciaSoftwareTest() {
        LicenciaSoftware licenciaExistente = new LicenciaSoftware();
        licenciaExistente.setId(1L);
        licenciaExistente.setNombre("Windows 11 Pro");
        licenciaExistente.setMarca("Microsoft");
        licenciaExistente.setVersion("11 Pro");
        licenciaExistente.setSerial("WIN11-PRO-ABC123");

        LicenciaSoftwareDTO licenciaDTO = new LicenciaSoftwareDTO();
        licenciaDTO.setNombre("Windows 11 Pro");
        licenciaDTO.setMarca("Microsoft");
        licenciaDTO.setVersion("11 Pro Actualizada");
        licenciaDTO.setSerial("WIN11-PRO-ABC123");

        when(licenciaSoftwareRepository.findById(1L)).thenReturn(Optional.of(licenciaExistente));
        when(licenciaSoftwareRepository.save(any(LicenciaSoftware.class))).thenAnswer(invocation -> invocation.getArgument(0));

        LicenciaSoftware resultado = licenciaSoftwareService.actualizarLicencia(1L, licenciaDTO);

        assertNotNull(resultado);
        assertEquals("Windows 11 Pro", resultado.getNombre());
        assertEquals("Microsoft", resultado.getMarca());
        assertEquals("11 Pro Actualizada", resultado.getVersion());
        assertEquals("WIN11-PRO-ABC123", resultado.getSerial());

        verify(licenciaSoftwareRepository, times(1)).findById(1L);
        verify(licenciaSoftwareRepository, times(1)).save(any(LicenciaSoftware.class));
    }

    @Test
    void eliminarLicenciaSoftwareTest() {
        doNothing().when(licenciaSoftwareRepository).deleteById(1L);

        licenciaSoftwareService.eliminarLicencia(1L);

        verify(licenciaSoftwareRepository, times(1)).deleteById(1L);
    }
}