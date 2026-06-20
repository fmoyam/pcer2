package com.pcer2.service_estadisticas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteClientesDTO {

    @Schema(description = "Nombre del cliente que mas servicios ha solicitado", example = "Juan Perez")
    private String clienteMasFiel;

    @Schema(description = "Nombre del cliente que mas equipos ha traido", example = "Rosa Gutierrez")
    private String clienteMasEquipos;

    @Schema(description = "Nombre del mas antiguo en base de datos 'pc_clientes'.", example = "Catalina Mora")
    private String clienteMasAntiguo;

    @Schema(description = "Nombre del mas antiguo en base de datos 'pc_clientes'.", example = "Ignacio Fernandez")
    private String ultimoClienteRegistrado;

    @Schema(description = "Cantidad de ordenes del cliente mas fiel", example = "12")
    private Integer ordenesClienteMasFiel;

    @Schema(description = "Cantidad de equipos del cliente que mas dispositivos ha traido", example = "6")
    private Integer cantidadEquiposClienteMasEquipos;

}

