package com.pcer2.service_mantenimiento.model;

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
@Data
@Table(name = "productos_mantenimiento")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un producto o insumo de mantenimiento")
public class ProductoMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del producto de mantenimiento", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto de mantenimiento", example = "Pasta térmica")
    private String nombre;

    @Schema(description = "Categoría del producto", example = "Insumo técnico")
    private String categoria;

    @Schema(description = "Descripción del producto", example = "Pasta térmica para mantenimiento de CPU y GPU")
    private String descripcion;

    @Column(name = "stock_actual")
    @Schema(description = "Cantidad disponible en stock", example = "25")
    private Integer stockActual;

    @Column(name = "precio_unitario")
    @Schema(description = "Precio unitario del producto", example = "4500")
    private Double precioUnitario;
    
}