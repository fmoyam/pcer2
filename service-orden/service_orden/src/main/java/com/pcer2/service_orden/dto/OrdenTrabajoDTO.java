package com.pcer2.service_orden.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO utilizado para crear o actualizar una orden de trabajo")
public class OrdenTrabajoDTO {

    @Schema(description = "ID del cliente asociado a la orden", example = "1")
    private Long clienteId;

    @Schema(description = "ID del equipo asociado a la orden", example = "1")
    private Long equipoId;

    @Schema(description = "ID del servicio técnico asociado a la orden", example = "1")
    private Long servicioId;

    @Schema(description = "ID de la licencia de software asociada a la orden", example = "1")
    private Long softwareId;

    @Schema(description = "ID del producto de mantenimiento asociado a la orden", example = "1")
    private Long productoMantenimientoId;

    @Schema(description = "Descripción del problema reportado", example = "Equipo requiere mantenimiento completo e instalación de software")
    private String descripcionProblema;

    @Schema(description = "Fecha de ingreso de la orden", example = "2026-06-14")
    private LocalDate fechaIngreso;

    @Schema(description = "Fecha estimada de entrega del equipo", example = "2026-06-17")
    private LocalDate fechaEntregaEstimada;

    @Schema(description = "Estado actual de la orden", example = "Ingresada")
    private String estado;

    @Schema(description = "Precio total asociado a la orden", example = "39500")
    private Double precioTotal;
}