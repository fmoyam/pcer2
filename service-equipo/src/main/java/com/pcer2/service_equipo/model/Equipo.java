package com.pcer2.service_equipo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_equipo_id")
    private TipoEquipo tipoEquipo;

    private String marca;
    private String modelo_cpu;
    private String placa_madre;
    private int ram_cantidad;
    private int ram_frecuencia;
    private int almacen_cantidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_almacen_id")    
    private TipoAlmacen almacen_tipo;
    
    @Column(unique = true)
    private String numeroserie;
    
    private int veces_reparado;

}
