package com.pcer2.service_estadisticas.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EquipoDTO {

    @Schema(description = "ID único autoincremental.", example = "1")
    private Long id;

    @Schema(description = "Tipo de equipo informatico.", example = "Notebook")
    private String tipoEquipo;

    @Schema(description = "Fabricante del equipo", example = "Lenovo") 
    private String marca;

    @Schema(description = "Tipo de almacenamiento usado por el equipo", example = "SSD")
    private String tipoAlmacen;

    @Schema(description = "Cantidad de veces que ha sido reparado el equipo", example = "2")
    private int veces_reparado;
}
