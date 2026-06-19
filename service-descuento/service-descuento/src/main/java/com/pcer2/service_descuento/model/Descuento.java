package com.pcer2.service_descuento.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "codigo_descuento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Modelo que representa un código de descuento en el sistema")
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID autoincremental de la base de datos", example = "1")
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Código único del cupón (en mayúsculas)", example = "PROMO2026")
    private String codigo;

    @Schema(description = "Descripción corta de lo que ofrece el cupón", example = "Descuento de bienvenida para el año 2026")
    private String descripcion;

    @Schema(description = "Porcentaje de descuento que aplica (de 1 a 100)", example = "15")
    private Integer porcentajeDescuento;

    @Schema(description = "Fecha en la que el cupón empieza a ser válido (YYYY-MM-DD)", example = "2026-01-01")
    private LocalDate fechaInicio;

    @Schema(description = "Fecha de expiración del cupón. Si es null, nunca expira (YYYY-MM-DD)", example = "2026-12-31", nullable = true)
    private LocalDate fechaFin;

    @Schema(description = "Estado lógico del cupón. Si es false, no se puede usar aunque esté en fecha", example = "true")
    private Boolean activo;
}
