package com.pcer2.service_hardware.controller;
import java.util.List;
import java.util.Optional;
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
import com.pcer2.service_hardware.model.Hardware;
import com.pcer2.service_hardware.service.HardwareService;

@RestController
@RequestMapping("/api/v1/hardware")
public class HardwareController {

    @Autowired
    private HardwareService hardwareService;

    @GetMapping
    public List<Hardware> listar() {
        return hardwareService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hardware> buscarPorId(@PathVariable Long id) {

        Optional<Hardware> hardware = hardwareService.buscarPorId(id);

        return hardware.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Hardware> crear(@RequestBody Hardware hardware) {
        return ResponseEntity.ok(hardwareService.guardar(hardware));
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        hardwareService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}