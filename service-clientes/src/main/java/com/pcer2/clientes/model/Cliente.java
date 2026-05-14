package com.pcer2.clientes.model;
import com.pcer2.clientes.dto.EquipoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String rut;
    
    private int telefono;
    private String email;
    private LocalDate fecha_registro;
    private int ordenes_totales;
    
    @Transient  // No persiste en la base de datos de clientes
    private List<EquipoDTO> equipos;
}
