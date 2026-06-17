package com.pcer2.clientes.dto;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDTO {

    @Schema(description = "ID único autoincremental.", example = "1")
    private Long id;

    @Schema(description = "Tipo de equipo informatico.", example = "Notebook") 
    private String tipoEquipo;

    @Schema(description = "Fabricante del equipo", example = "Lenovo") 
    private String marca;

    @Schema(description = "Modelo de procesador / CPU", example = "Intel Core i5-4460") 
    private String modelo_cpu;

    @Schema(description = "Modelo de placa madre / motherboard", example = "Asus B450M-A") 
    private String placa_madre;

    @Schema(description = "Cantidad de memoria RAM en gigabytes", example = "16") 
    private int ram_cantidad;

    @Schema(description = "Frecuencia de memoria RAM en mhz", example = "4800") 
    private int ram_frecuencia;

    @Schema(description = "Cantidad de almacenamiento en gigabytes", example = "1024") 
    private int almacen_cantidad;

    @Schema(description = "Tipo de almacenamiento usado por el equipo", example = "HDD") 
    private String tipoAlmacen;

    @Schema(description = "Cantidad de veces que ha sido reparado el equipo", example = "2") 
    private int veces_reparado;
}