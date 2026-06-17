package com.pcer2.service_estadisticas.dto;

import lombok.Data;

@Data
public class VoucherDTO {

    private Long id;

    private String metodoPago;

    private Double total;
}
