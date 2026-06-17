package com.pcer2.service_hardware.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pcer2.service_hardware.model.Hardware;
import com.pcer2.service_hardware.service.HardwareService;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/hardware")
public class HardwareController {

    @Autowired
    private HardwareService hardwareService;

    @Operation(summary = "Lista todo el hardware inventariado.", description = "Retorna lista con todo el hardware en inventario de 'pc_hardware'.")
    @GetMapping
    public List<Hardware> listar() {
        return hardwareService.listarTodos();
    }

    @Operation(summary = "Busca un dispositivo mediante su ID", description = "Retorna el dispositivo buscado por ID en la base de datos de 'pc_hardware'.")
    @GetMapping("/{id}")
    public ResponseEntity<Hardware> buscarPorId(@PathVariable Long id) {

        Optional<Hardware> hardware = hardwareService.buscarPorId(id);

        return hardware.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Agrega un dispositivo", description = "Agrega un dispositivo a la base de datos de 'pc_hardware'.")
    @PostMapping
    public ResponseEntity<Hardware> crear(@RequestBody Hardware hardware) {
        return ResponseEntity.ok(hardwareService.guardar(hardware));
    }

    @Operation(summary = "Modifica los datos de un dispositivo", description = "Permite editar los datos un dispositivo en la base de datos de 'pc_hardware'.")
    @PutMapping("/{id}")
    public ResponseEntity<Hardware> actualizar(
            @PathVariable Long id,
            @RequestBody Hardware hardware) {

        Hardware actualizado = hardwareService.actualizar(id, hardware);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Elimina un dispositivo mediante su ID", description = "Elimina de inventario un dispositivo. Si su cantidad es > 1 se descuenta, de lo contrario se borra definitivamente.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHardware(@PathVariable Long id) {
        String resultado = hardwareService.eliminar(id);
        
        if (resultado.contains("no encontrado")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
        } else if (resultado.contains("Cantidad reducida")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.ok(resultado); // 200 OK
        }
    }

}