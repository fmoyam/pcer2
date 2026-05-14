package com.pcer2.service_servicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioDTO {//El DTO solo sirve para recibir datos desde Postman.

    private String nombre;
    private String descripcion;
    private Double precioBase;
    private Boolean activo;
}
