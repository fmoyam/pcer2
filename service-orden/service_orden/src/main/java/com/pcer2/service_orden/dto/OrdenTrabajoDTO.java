package com.pcer2.service_orden.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenTrabajoDTO {

    private Long clienteId;
    private Long equipoId;
    private Long servicioId;
    private String descripcionProblema;
    private LocalDate fechaIngreso;
    private LocalDate fechaEntregaEstimada;
    private String estado;
    private Double precioTotal;
}