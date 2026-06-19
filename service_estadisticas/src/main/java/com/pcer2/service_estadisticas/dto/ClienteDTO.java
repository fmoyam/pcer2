package com.pcer2.service_estadisticas.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClienteDTO {

    @Schema(description = "ID único autoincremental.", example = "1") 
    private Long id;

    @Schema(description = "Nombre del cliente", example = "Juan") 
    private String nombre;

    @Schema(description = "Apellido del cliente", example = "Perez") 
    private String apellido;

    @Schema(description = "RUT/RUN del cliente", example = "12.345.678-9") 
    private String rut;

    @Schema(description = "Fecha de registro en base de datos", example = "17/05/2024")
    private String fecha_registro;

    @Schema(description = "Cantidad de ordenes (servicios) solicitados por el cliente", example = "3") 
    private int ordenes_totales;

    @Schema(description = "Lista de equipos del cliente. Se solicitan desde el microservicio 'equipos'.") 
    private List<EquipoDTO> equipos;
}
