package com.pcer2.service_auth.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pcer2.service_auth.model.Rol;
import com.pcer2.service_auth.model.Usuario;
import com.pcer2.service_auth.repository.UsuarioRepository;

// Activa Mockito para poder usar @Mock e @InjectMocks
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    // Simula el repositorio para no conectarnos a la base de datos real
    @Mock
    private UsuarioRepository usuarioRepo;

     // Simula el servicio que genera el token JWT
    @Mock
    private JwtService jwtService;

    // Simula el encriptador de contraseñas
    @Mock
    private PasswordEncoder passwordEncoder;

    // Crea el AuthService real, pero usando los mocks de arriba
    @InjectMocks
    private AuthService authService;

    @Test
    void registrarUsuarioTest() {

        // Arrange: preparamos un usuario de prueba
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("admin");
        usuario.setContrasena("1234");
        usuario.setCorreo("admin@pcer2.cl");

        // Simulamos que la contraseña se encripta
        when(passwordEncoder.encode("1234")).thenReturn("claveEncriptada");
        // Simulamos que el repositorio guarda el usuario
        when(usuarioRepo.save(any(Usuario.class))).thenReturn(usuario);

        // Act: ejecutamos el método real del service
        String resultado = authService.registrar(usuario);

         // Assert: verificamos que el resultado sea el esperado
        assertEquals("Usuario registrado", resultado);

        // Verificamos que se haya encriptado la contraseña una vez
        verify(passwordEncoder, times(1)).encode("1234");
        // Verificamos que se haya guardado el usuario una vez
        verify(usuarioRepo, times(1)).save(any(Usuario.class));
    }



    @Test
    void loginCorrectoTest() {
        // Arrange: creamos un rol de prueba
        Rol rol = new Rol();
        rol.setNombreRol("ADMIN");

         // Creamos un usuario de prueba con contraseña ya encriptada
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("admin");
        usuario.setContrasena("claveEncriptada");
        usuario.setCorreo("admin@pcer2.cl");
        usuario.setRoles(Set.of(rol));

        // Simulamos que el usuario existe en la base de datos
        when(usuarioRepo.findByNombreUsuario("admin")).thenReturn(Optional.of(usuario));
         // Simulamos que la contraseña ingresada coincide con la encriptada
        when(passwordEncoder.matches("1234", "claveEncriptada")).thenReturn(true);
        // Simulamos que JwtService genera un token
        when(jwtService.generarToken(eq("admin"), anyList())).thenReturn("token-falso");

        // Act: ejecutamos el login
        String resultado = authService.login("admin", "1234");

        // Assert: esperamos recibir el token falso
        assertEquals("token-falso", resultado);

         // Verificamos que se buscó el usuario
        verify(usuarioRepo, times(1)).findByNombreUsuario("admin");
        // Verificamos que se comparó la contraseña
        verify(passwordEncoder, times(1)).matches("1234", "claveEncriptada");
        // Verificamos que se generó el token
        verify(jwtService, times(1)).generarToken(eq("admin"), anyList());
    }



    @Test
    void loginUsuarioNoEncontradoTest() {
        // Arrange: simulamos que el usuario no existe
        when(usuarioRepo.findByNombreUsuario("admin")).thenReturn(Optional.empty());

         // Act + Assert: esperamos que lance error al no encontrar el usuario
        assertThrows(RuntimeException.class, () -> {
            authService.login("admin", "1234");
        });

        // Verificamos que intentó buscar el usuario una vez
        verify(usuarioRepo, times(1)).findByNombreUsuario("admin");
    }



    @Test
    void loginCredencialesInvalidasTest() {
        // Arrange: creamos un usuario existente
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("admin");
        usuario.setContrasena("claveEncriptada");

        // Simulamos que el usuario sí existe
        when(usuarioRepo.findByNombreUsuario("admin")).thenReturn(Optional.of(usuario));
        // Simulamos que la contraseña ingresada no coincide
        when(passwordEncoder.matches("1234", "claveEncriptada")).thenReturn(false);

        // Act + Assert: esperamos error por credenciales inválidas
        assertThrows(RuntimeException.class, () -> {
            authService.login("admin", "1234");
        });

         // Verificamos que buscó el usuario
        verify(usuarioRepo, times(1)).findByNombreUsuario("admin");
        // Verificamos que comparó la contraseña
        verify(passwordEncoder, times(1)).matches("1234", "claveEncriptada");
    }
}