package com.pcer2.service_estadisticas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteClientesDTO {

    private String clienteMasFiel;

    private String clienteMasEquipos;

    private String clienteMasAntiguo;

    private String ultimoClienteRegistrado;

    private Integer ordenesClienteMasFiel;

    private Integer cantidadEquiposClienteMasEquipos;

}

