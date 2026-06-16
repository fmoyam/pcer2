package com.pcer2.service_estadisticas.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteVoucherDTO {

    private String metodoPagoMasUsado;

    private Double ticketPromedio;
}
