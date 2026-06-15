package com.pcer2.service_mantenimiento.model;

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
@Table(name = "productos_mantenimiento")
@AllArgsConstructor
@NoArgsConstructor
public class ProductoMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String categoria;

    private String descripcion;

    @Column(name = "stock_actual")
    private Integer stockActual;

    @Column(name = "precio_unitario")
    private Double precioUnitario;
}