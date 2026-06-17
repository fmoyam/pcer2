package com.pcer2.service_estadisticas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pcer2.service_estadisticas.model.Estadistica;
import com.pcer2.service_estadisticas.service.EstadisticasService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/estadisticas")
public class EstadisticaController {

    @Autowired
    private EstadisticasService service;

    @PostMapping("/clientes")
    public Estadistica generarClientes() throws Exception {
        return service.generarReporteClientes();
    }

    @PostMapping("/equipos")
    public Estadistica generarEquipos() throws Exception {
        return service.generarReporteEquipos();
    }

    @PostMapping("/vouchers")
    public Estadistica generarVoucher() throws Exception {
        return service.generarReporteVoucher();
    }

    @GetMapping
    public List<Estadistica> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estadistica> buscar(
            @PathVariable Long id) {

        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
