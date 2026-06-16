package com.pcer2.service_software.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pcer2.service_software.dto.LicenciaSoftwareDTO;
import com.pcer2.service_software.model.LicenciaSoftware;
import com.pcer2.service_software.service.LicenciaSoftwareService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/software")
@CrossOrigin(origins = "*")
//permitir que Swagger pueda llamar al Controller desde otro puerto
@Tag(name = "Gestión de Software", description = "Endpoints para administrar licencias de software")
public class LicenciaSoftwareController {

    @Autowired
    private LicenciaSoftwareService licenciaSoftwareService;

    @GetMapping
    @Operation(summary = "Listar licencias de software", description = "Obtiene todas las licencias de software registradas en el sistema")
    public List<LicenciaSoftware> listar() {
        return licenciaSoftwareService.listarTodos();
    }

    @PostMapping
    @Operation(summary = "Crear licencia de software", description = "Registra una nueva licencia de software en el sistema")
    public ResponseEntity<LicenciaSoftware> crear(@RequestBody LicenciaSoftwareDTO licenciaSoftwareDTO) {
        return ResponseEntity.ok(licenciaSoftwareService.guardar(licenciaSoftwareDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar licencia por ID", description = "Obtiene una licencia de software específica según su ID")
    public ResponseEntity<LicenciaSoftware> obtenerPorId(@PathVariable Long id) {
        return licenciaSoftwareService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar licencia de software", description = "Actualiza los datos de una licencia de software existente")
    public ResponseEntity<LicenciaSoftware> actualizar(@PathVariable Long id, @RequestBody LicenciaSoftwareDTO licenciaSoftwareDTO) {
        LicenciaSoftware licenciaActualizada = licenciaSoftwareService.actualizarLicencia(id, licenciaSoftwareDTO);

        if (licenciaActualizada != null) {
            return ResponseEntity.ok(licenciaActualizada);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar licencia de software", description = "Elimina una licencia de software según su ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        licenciaSoftwareService.eliminarLicencia(id);
        return ResponseEntity.noContent().build();
    }
}