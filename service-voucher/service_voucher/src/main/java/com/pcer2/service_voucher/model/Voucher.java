package com.pcer2.service_voucher.model;

import java.time.LocalDate;

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
@Table(name = "voucher_trabajo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID de la orden que viene desde service_orden
    @Column(name = "orden_id")
    private Long ordenId;

    private LocalDate fechaEmision;
    private String metodoPago;
    private Double total;//precio total por servicio
    private Integer cantidadServicios;
    private String estado;// Estado final del producto
    private String observacion;

    // No se guarda en la base de datos.
    // Sirve para mostrar datos traídos desde service_orden con WebClient.
    @Transient
    private Object datosOrden;

    @Column(name = "codigo_descuento")
    private String codigoDescuento;

    @Transient
    private Object datosDescuento;
}
