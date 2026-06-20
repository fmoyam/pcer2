package com.pcer2.service_voucher.model;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "voucher_trabajo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo un voucher para el cliente final")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autoincremental de la base de datos", example = "1")
    private Long id;

    @Schema(description = "ID obtenido desde el microservicio 'service-orden'.")
    @Column(name = "orden_id")
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

    @Schema(description = "Datos obtenidos desde el microservicio 'service-orden'.")
    @Transient
    private Object datosOrden;

    @Schema(description = "Codigo de descuento opcional", example = "DIADELPADRE2026")
    @Column(name = "codigo_descuento")
    private String codigoDescuento;

    @Schema(description = "Datos obtenidos desde el microservicio 'service-descuento'.")
    @Transient
    private Object datosDescuento;
}
