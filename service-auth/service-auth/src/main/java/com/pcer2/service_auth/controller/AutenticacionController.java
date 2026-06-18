package com.pcer2.service_auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcer2.service_auth.dto.AuthRequest;
import com.pcer2.service_auth.model.Usuario;
import com.pcer2.service_auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticacion", description = "Endpoints para regristro y logis de usuarios")
public class AutenticacionController {

    @Autowired
    private AuthService authService;
    @Operation(summary = "Registrar un nuevo usuario", description = "Guardar el usuario con la contraseña encriptada")
    @PostMapping("/registrar")
    public ResponseEntity<String> regristrar(@RequestBody Usuario usuario){
        return ResponseEntity.ok(authService.regristrar(usuario));
    }
    @Operation(summary = "Inicia sesion", description = "Retorna un token JWT si las credenciales son válidas")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request){
        try{
            String token = authService.login(request.getNombreUsuario(), request.getPassword());
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }



}
