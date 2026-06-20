package com.pcer2.service_estadisticas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VoucherDTO {

    @Schema(description = "ID único autoincremental.", example = "1")
    private Long id;

    @Schema(description = "Metodo de pago usado por el cliente", example = "Efectivo")
    private String metodoPago;

    @Schema(description = "Total en pesos a pagar por el servicio", example = "35000,0")
    private Double total;
}
