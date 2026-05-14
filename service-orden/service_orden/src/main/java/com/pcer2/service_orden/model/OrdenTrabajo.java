package com.pcer2.service_orden.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ordenes_trabajo")
@AllArgsConstructor
@NoArgsConstructor
public class OrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(name = "equipo_id")
    private Long equipoId;

    @Column(name = "servicio_id")
    private Long servicioId;

    @Column(name = "descripcion_problema")
    private String descripcionProblema;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "fecha_entrega_estimada")
    private LocalDate fechaEntregaEstimada;

    private String estado;

    @Column(name = "precio_total")
    private Double precioTotal;
}