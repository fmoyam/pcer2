package com.pcer2.service_estadisticas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteEquiposDTO {

    @Schema(description = "El tipo de dispositivo que mas solicita servicios", example = "Notebook")
    private String tipoEquipoMasComun;

    @Schema(description = "Fabricante que mas se repite en servicios", example = "Acer")
    private String marcaMasComun;

    @Schema(description = "Tipo de almacenamiento que mas se repite en diferentes equipos", example = "HDD")
    private String tipoAlmacenMasComun;

    @Schema(description = "ID del equipo que mas fallas ha tenido", example = "2")
    private Long equipoMasReparado;
}
