package com.pcer2.service_voucher.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.pcer2.service_voucher.dto.VoucherDTO;
import com.pcer2.service_voucher.model.Voucher;
import com.pcer2.service_voucher.repository.VoucherRepository;

@Service
public class VoucherService {

     private final VoucherRepository voucherRepository;
    private final WebClient webClient;

    public VoucherService(VoucherRepository voucherRepository, WebClient webClient) {
        this.voucherRepository = voucherRepository;
        this.webClient = webClient;
    }

    // GET ALL
    public List<Voucher> obtenerTodos() {

        List<Voucher> vouchers = voucherRepository.findAll();

        for (Voucher voucher : vouchers) {

            Object orden = webClient
                    .get()
                    .uri("http://localhost:8084/api/v1/ordenes/" + voucher.getOrdenId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

            voucher.setDatosOrden(orden);
        }

        return vouchers;
    }

    // GET BY ID
    public Optional<Voucher> obtenerPorId(Long id) {

        Optional<Voucher> voucherOptional = voucherRepository.findById(id);

        if (voucherOptional.isPresent()) {

            Voucher voucher = voucherOptional.get();

            Object orden = webClient
                    .get()
                    .uri("http://localhost:8084/api/v1/ordenes/" + voucher.getOrdenId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();

            voucher.setDatosOrden(orden);
        }

        return voucherOptional;
    }

    // POST
    public Voucher guardar(VoucherDTO dto) {

        Voucher voucher = new Voucher();

        voucher.setOrdenId(dto.getOrdenId());
        voucher.setFechaEmision(dto.getFechaEmision());
        voucher.setMetodoPago(dto.getMetodoPago());
        voucher.setTotal(dto.getTotal());
        voucher.setCantidadServicios(dto.getCantidadServicios());
        voucher.setEstado(dto.getEstado());
        voucher.setObservacion(dto.getObservacion());

        return voucherRepository.save(voucher);
    }

    // PUT
    public Voucher actualizar(Long id, VoucherDTO dto) {

        Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voucher no encontrado"));

        voucher.setOrdenId(dto.getOrdenId());
        voucher.setFechaEmision(dto.getFechaEmision());
        voucher.setMetodoPago(dto.getMetodoPago());
        voucher.setTotal(dto.getTotal());
        voucher.setCantidadServicios(dto.getCantidadServicios());
        voucher.setEstado(dto.getEstado());
        voucher.setObservacion(dto.getObservacion());

        return voucherRepository.save(voucher);
    }

    // DELETE
    public void eliminar(Long id) {
        voucherRepository.deleteById(id);
    }
}
