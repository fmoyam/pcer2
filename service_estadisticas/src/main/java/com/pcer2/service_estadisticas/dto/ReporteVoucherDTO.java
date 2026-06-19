package com.pcer2.service_estadisticas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteVoucherDTO {

    @Schema(description = "Metodo de pago mas utilizado por clientes", example = "Debito")
    private String metodoPagoMasUsado;

    @Schema(description = "Valor promedio que pagan los clientes por servicio", example = "24450,0")
    private Double ticketPromedio;
}
