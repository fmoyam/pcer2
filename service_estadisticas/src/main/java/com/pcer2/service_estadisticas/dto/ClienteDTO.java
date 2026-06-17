package com.pcer2.service_estadisticas.dto;

import java.util.List;
import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String rut;

    private String fecha_registro;

    private int ordenes_totales;

    private List<EquipoDTO> equipos;
}
