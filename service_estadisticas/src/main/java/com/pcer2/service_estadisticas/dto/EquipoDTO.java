package com.pcer2.service_estadisticas.dto;
import lombok.Data;

@Data
public class EquipoDTO {

    private Long id;

    private String tipoEquipo;

    private String marca;

    private String tipoAlmacen;

    private int veces_reparado;
}
