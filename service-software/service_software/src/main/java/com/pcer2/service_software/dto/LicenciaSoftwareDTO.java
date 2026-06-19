package com.pcer2.service_software.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO utilizado para crear o actualizar una licencia de software")
public class LicenciaSoftwareDTO {

    @Schema(description = "Nombre del software", example = "Windows 11 Pro")
    private String nombre;

    @Schema(description = "Marca o proveedor del software", example = "Microsoft")
    private String marca;

    @Schema(description = "Versión del software", example = "11 Pro")
    private String version;

    @Schema(description = "Serial único de la licencia", example = "WIN11-PRO-ABC123")
    private String serial;
}