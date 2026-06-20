package com.pcer2.service_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pcer2.service_auth.dto.AuthRequest;
import com.pcer2.service_auth.model.Usuario;
import com.pcer2.service_auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Autenticación", description = "Endpoints para registro y login de usuarios")
public class AutenticacionController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registrar")
    @Operation(summary = "Registrar usuario", description = "Guarda un usuario con la contraseña encriptada")
    public ResponseEntity<String> registrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(authService.registrar(usuario));
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Retorna un token JWT si las credenciales son válidas")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        try {
            String token = authService.login(request.getNombreUsuario(), request.getPassword());
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}