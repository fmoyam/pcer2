package com.pcer2.service_equipo.model;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "ID único autoincremental.", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Tipo de equipo informatico.", example = "Notebook")
    private String tipoEquipo;

    @Schema(description = "Fabricante del equipo", example = "Lenovo") 
    private String marca;

    @Schema(description = "Modelo de procesador / CPU", example = "Intel Core i5-4460") 
    private String modelo_cpu;

    @Schema(description = "Modelo de placa madre / motherboard", example = "Asus B450M-A")
    private String placa_madre;

    @Schema(description = "Cantidad de memoria RAM en gigabytes", example = "16") 
    private int ram_cantidad;

    @Schema(description = "Frecuencia de memoria RAM en mhz", example = "4800") 
    private int ram_frecuencia;

    @Schema(description = "Cantidad de almacenamiento en gigabytes", example = "1024") 
    private int almacen_cantidad; 

    @Schema(description = "Tipo de almacenamiento usado por el equipo", example = "SSD")
    private String tipoAlmacen;
    
    @Schema(description = "Numero serial unico del dispositivo", example = "XYZ12A34B56C78D")
    @Column(unique = true)
    private String numeroserie;
    
    @Schema(description = "Cantidad de veces que ha sido reparado el equipo", example = "2")
    private int veces_reparado;

    @Schema(description = "ID del cliente dueño del equipo. Se solicita ID desde el microservicio 'clientes'.", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;    

    
}
