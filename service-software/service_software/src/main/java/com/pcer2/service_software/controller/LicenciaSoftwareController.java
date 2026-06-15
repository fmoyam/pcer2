package com.pcer2.service_software.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/v1/software")
public class LicenciaSoftwareController {

    @Autowired
    private LicenciaSoftwareService licenciaSoftwareService;

    @GetMapping
    public List<LicenciaSoftware> listar() {
        return licenciaSoftwareService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<LicenciaSoftware> crear(@RequestBody LicenciaSoftwareDTO licenciaSoftwareDTO) {
        return ResponseEntity.ok(licenciaSoftwareService.guardar(licenciaSoftwareDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicenciaSoftware> obtenerPorId(@PathVariable Long id) {
        return licenciaSoftwareService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LicenciaSoftware> actualizar(@PathVariable Long id, @RequestBody LicenciaSoftwareDTO licenciaSoftwareDTO) {
        LicenciaSoftware licenciaActualizada = licenciaSoftwareService.actualizarLicencia(id, licenciaSoftwareDTO);

        if (licenciaActualizada != null) {
            return ResponseEntity.ok(licenciaActualizada);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        licenciaSoftwareService.eliminarLicencia(id);
        return ResponseEntity.noContent().build();
    }
}