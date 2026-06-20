package com.pcer2.service_estadisticas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pcer2.service_estadisticas.model.Estadistica;
import com.pcer2.service_estadisticas.service.EstadisticasService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/estadisticas")
public class EstadisticaController {

    @Autowired
    private EstadisticasService service;

    @Operation(summary = "Crea un nuevo reporte estadistico de categoria 'CLIENTES'.", description = "Usando datos del microservicio 'clientes' elabora un informe con datos resumidos del mismo.")
    @PostMapping("/clientes")
    public Estadistica generarClientes() throws Exception {
        return service.generarReporteClientes();
    }

    @Operation(summary = "Crea un nuevo reporte estadistico de categoria 'EQUIPOS'.", description = "Usando datos del microservicio 'equipos' elabora un informe con datos resumidos del mismo.")
    @PostMapping("/equipos")
    public Estadistica generarEquipos() throws Exception {
        return service.generarReporteEquipos();
    }

    @Operation(summary = "Crea un nuevo reporte estadistico de categoria 'VOUCHERS'.", description = "Usando datos del microservicio 'vouchers' elabora un informe con datos resumidos del mismo.")
    @PostMapping("/vouchers")
    public Estadistica generarVoucher() throws Exception {
        return service.generarReporteVoucher();
    }

    @Operation(summary = "Lista todos los reportes creados.", description = "Obtiene todos los reportes estadisticos de 'pc_estadisticas'.")
    @GetMapping
    public List<Estadistica> listar() {
        return service.listar();
    }

    @Operation(summary = "Busca un reporte mediante su ID", description = "Filtra reportes mediante ID en la BD 'pc_estadisticas'.")
    @GetMapping("/{id}")
    public ResponseEntity<Estadistica> buscar(
            @PathVariable Long id) {

        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Elimina un reporte mediante su ID", description = "Elimina los datos de un reporte en BD 'pc_estadisticas'.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
