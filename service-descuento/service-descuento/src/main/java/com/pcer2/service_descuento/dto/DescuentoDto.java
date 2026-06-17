package com.pcer2.service_descuento.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DescuentoDto {

    private String codigo;
    private String descripcion;
    private Integer porcentajeDescuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Boolean activo;
}
