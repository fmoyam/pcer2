package com.pcer2.service_descuento.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Objeto de transferencia de datos para la creación de un descuento")
public class DescuentoDto {

    @Schema(description = "Código único del cupón", example = "PROMO2026")
    private String codigo;

    @Schema(description = "Descripción del cupón", example = "Descuento de bienvenida para el año 2026")
    private String descripcion;

    @Schema(description = "Porcentaje a descontar", example = "15")
    private Integer porcentajeDescuento;

    @Schema(description = "Fecha de inicio del beneficio (YYYY-MM-DD)", example = "2026-01-01")
    private LocalDate fechaInicio;

    @Schema(description = "Fecha de finalización (YYYY-MM-DD). Puede ser null.", example = "2026-12-31", nullable = true)
    private LocalDate fechaFin;

    @Schema(description = "Indica si el cupón se crea activo o pausado", example = "true")
    private Boolean activo;
}
