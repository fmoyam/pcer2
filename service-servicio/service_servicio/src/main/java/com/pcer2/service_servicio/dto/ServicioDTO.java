package com.pcer2.service_servicio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO utilizado para crear o actualizar un servicio técnico")
public class ServicioDTO {//El DTO solo sirve para recibir datos desde Postman.

    @Schema(description = "Nombre del servicio técnico", example = "Mantención completa de equipo")
    private String nombre;

    @Schema(description = "Descripción del servicio técnico", example = "Limpieza completa del hardware del equipo. Incluye cambio de pasta térmica a CPU y GPU.")
    private String descripcion;

    @Schema(description = "Precio base del servicio técnico", example = "35000")
    private Double precioBase;

    @Schema(description = "Indica si el servicio se encuentra activo", example = "true")
    private Boolean activo;
}
