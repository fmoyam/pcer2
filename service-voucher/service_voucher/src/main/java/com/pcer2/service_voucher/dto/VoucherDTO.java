package com.pcer2.service_voucher.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDTO {

    private Long ordenId;
    private LocalDate fechaEmision;
    private String metodoPago;
    private Double total;
    private Integer cantidadServicios;
    private String estado;
    private String observacion;

    private String codigoDescuento;
}
