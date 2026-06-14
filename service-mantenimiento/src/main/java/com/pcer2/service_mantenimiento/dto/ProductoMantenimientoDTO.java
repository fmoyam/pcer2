package com.pcer2.service_mantenimiento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoMantenimientoDTO {

    private String nombre;
    private String categoria;
    private String descripcion;
    private Integer stockActual;
    private Double precioUnitario;
}