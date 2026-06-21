package com.pcer2.service_orden.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ordenes_trabajo")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una orden de trabajo del servicio técnico")
public class OrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la orden de trabajo", example = "1")
    private Long id;

    @Column(name = "cliente_id")
    @Schema(description = "ID del cliente asociado a la orden", example = "1")
    private Long clienteId;

    @Column(name = "equipo_id")
    @Schema(description = "ID del equipo asociado a la orden", example = "1")
    private Long equipoId;

    @Column(name = "servicio_id")
    @Schema(description = "ID del servicio técnico asociado a la orden", example = "1")
    private Long servicioId;

    @Column(name = "software_id")
    @Schema(description = "ID de la licencia de software asociada a la orden", example = "1")
    private Long softwareId;    //MS nuevos

    @Column(name = "producto_mantenimiento_id")
    @Schema(description = "ID del producto de mantenimiento asociado a la orden", example = "1")
    private Long productoMantenimientoId;    //MS nuevos

    @Column(name = "descripcion_problema")
    @Schema(description = "Descripción del problema reportado", example = "Equipo requiere mantenimiento completo e instalación de software")
    private String descripcionProblema;

    @Column(name = "fecha_ingreso")
    @Schema(description = "Fecha de ingreso de la orden", example = "2026-06-14")
    private LocalDate fechaIngreso;

    @Column(name = "fecha_entrega_estimada")
    @Schema(description = "Fecha estimada de entrega del equipo", example = "2026-06-17")
    private LocalDate fechaEntregaEstimada;

    @Schema(description = "Estado actual de la orden", example = "Ingresada")
    private String estado;

    @Column(name = "precio_total")
    @Schema(description = "Precio total asociado a la orden", example = "39500")
    private Double precioTotal;

    @Transient
    @Schema(description = "Datos del cliente obtenidos desde service-clientes mediante WebClient")
    private Object datosCliente;

    @Transient
    @Schema(description = "Datos del equipo obtenidos desde service-equipo mediante WebClient")
    private Object datosEquipo;

    @Transient
    @Schema(description = "Datos del servicio técnico obtenidos desde service-servicio mediante WebClient")
    private Object datosServicio;
 //MS nuevos
    @Transient
    @Schema(description = "Datos del software obtenidos desde service-software mediante WebClient")
    private Object datosSoftware;

    @Transient
    @Schema(description = "Datos del producto de mantenimiento obtenidos desde service-mantenimiento mediante WebClient")
    private Object datosProductoMantenimiento;

}