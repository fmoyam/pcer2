package com.pcer2.clientes.dto;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDTO {
    private Long id;
    private String tipoEquipo;
    private String marca;
    private String modelo_cpu;
    private String placa_madre;
    private int ram_cantidad;
    private int ram_frecuencia;
    private int almacen_cantidad;
    private String tipoAlmacen;
    private int veces_reparado;
}