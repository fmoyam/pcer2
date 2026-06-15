package com.pcer2.service_equipo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoEquipo;  // pc, notebook, minipc, server, etc.
    private String marca;
    private String modelo_cpu;
    private String placa_madre;
    private int ram_cantidad;
    private int ram_frecuencia;
    private int almacen_cantidad;  
    private String tipoAlmacen; // ssd, hdd, microSD, emmc, etc.
    
    @Column(unique = true)
    private String numeroserie;
    
    private int veces_reparado;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;    

    
}
