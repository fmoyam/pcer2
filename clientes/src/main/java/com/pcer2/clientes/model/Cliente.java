package com.pcer2.clientes.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Cliente {

    private int id;
    private String nombre;
    private String apellido;
    private String rut;
    private int telefono;
    private String email;
    private LocalDate fecha_registro;
    private int ordenes_totales;

}
