package com.pcer2.service_mantenimiento.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO utilizado para crear o actualizar un producto de mantenimiento")
public class ProductoMantenimientoDTO {

    @Schema(description = "Nombre del producto de mantenimiento", example = "Pasta térmica")
    private String nombre;

    @Schema(description = "Categoría del producto", example = "Insumo técnico")
    private String categoria;

    @Schema(description = "Descripción del producto", example = "Pasta térmica para mantenimiento de CPU y GPU")
    private String descripcion;

    @Schema(description = "Cantidad disponible en stock", example = "25")
    private Integer stockActual;

    @Schema(description = "Precio unitario del producto", example = "4500")
    private Double precioUnitario;
    
}