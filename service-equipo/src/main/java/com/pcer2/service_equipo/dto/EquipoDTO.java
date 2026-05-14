package com.pcer2.service_equipo.dto;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDTO {
    private Long id;
    private Long clienteId;  // Referencia al cliente
    private TipoEquipoDTO tipoEquipo;
    private String marca;
    private String modelo_cpu;
    private String placa_madre;
    private int ram_cantidad;
    private int ram_frecuencia;
    private int almacen_cantidad;
    private TipoAlmacenDTO almacen_tipo;
    private String numeroserie;
    private int veces_reparado;
}
