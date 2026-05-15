package com.pcer2.service_voucher.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pcer2.service_voucher.dto.VoucherDTO;
import com.pcer2.service_voucher.model.Voucher;
import com.pcer2.service_voucher.service.VoucherService;

@RestController
@RequestMapping("/api/v1/vouchers")
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    // GET ALL
    @GetMapping
    public List<Voucher> obtenerTodos() {
        return voucherService.obtenerTodos();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Voucher> obtenerPorId(@PathVariable Long id) {

        Optional<Voucher> voucher = voucherService.obtenerPorId(id);

        return voucher.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    public Voucher guardar(@RequestBody VoucherDTO dto) {
        return voucherService.guardar(dto);
    }

    // PUT
    @PutMapping("/{id}")
    public Voucher actualizar(@PathVariable Long id,
                              @RequestBody VoucherDTO dto) {

        return voucherService.actualizar(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        voucherService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}