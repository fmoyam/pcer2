package com.pcer2.service_voucher.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pcer2.service_voucher.dto.VoucherDTO;
import com.pcer2.service_voucher.model.Voucher;
import com.pcer2.service_voucher.service.VoucherService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/vouchers")
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @Operation(summary = "Lista todos los vouchers", description = "Obtiene todos los vouchers creados en BD 'pc_voucher'.")
    @GetMapping
    public List<Voucher> obtenerTodos() {
        return voucherService.obtenerTodos();
    }

    @Operation(summary = "Busca un voucher por su ID", description = "Filtra en base de datos mediante una ID especifica de voucher.")
    @GetMapping("/{id}")
    public ResponseEntity<Voucher> obtenerPorId(@PathVariable Long id) {

        Optional<Voucher> voucher = voucherService.obtenerPorId(id);

        return voucher.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crea un nuevo voucher", description = "Agrega un nuevo voucher a BD 'pc_voucher'.")
    @PostMapping
    public Voucher guardar(@RequestBody VoucherDTO dto) {
        return voucherService.guardar(dto);
    }

    @Operation(summary = "Modifica un voucher", description = "Modifica los datos de un ID ya presente en BD 'pc_voucher'.")
    @PutMapping("/{id}")
    public Voucher actualizar(@PathVariable Long id,
                              @RequestBody VoucherDTO dto) {

        return voucherService.actualizar(id, dto);
    }

    @Operation(summary = "Elimina un voucher mediante su ID", description = "Elimina de BD 'pc_voucher' un voucher usando su ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        voucherService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}