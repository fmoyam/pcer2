package com.pcer2.service_voucher.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDTO {

    @Schema(description = "ID obtenido desde el microservicio 'service-orden'.")
    private Long ordenId;

    @Schema(description = "Fecha en la que se emite el voucher (YYYY-MM-DD)", example = "2026-01-01")
    private LocalDate fechaEmision;

    @Schema(description = "Medio de pago usado por el cliente", example = "Transferencia")
    private String metodoPago;

    @Schema(description = "Precio final a pagar por el cliente", example = "34990.0")
    private Double total;

    @Schema(description = "Cantidad de servicios que solicito el cliente", example = "2")
    private Integer cantidadServicios;

    @Schema(description = "Estado final del servicio", example = "Finalizado y aprobado por cliente.")
    private String estado;

    @Schema(description = "Comentario adicional sobre el servicio realizado", example = "Cliente en confirmidad.")
    private String observacion;

    @Schema(description = "Codigo de descuento opcional", example = "DIADELPADRE2026")
    private String codigoDescuento;
}
