package com.pcer2.service_estadisticas.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteEquiposDTO {

    private String tipoEquipoMasComun;

    private String marcaMasComun;

    private String tipoAlmacenMasComun;

    private Long equipoMasReparado;
}
