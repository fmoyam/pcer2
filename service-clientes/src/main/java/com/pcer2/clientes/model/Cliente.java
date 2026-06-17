package com.pcer2.clientes.model;
import com.pcer2.clientes.dto.EquipoDTO;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "ID único autoincremental.", example = "1") 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del cliente", example = "Juan") 
    private String nombre;

    @Schema(description = "Apellido del cliente", example = "Perez") 
    private String apellido;

    @Schema(description = "RUT/RUN del cliente", example = "12.345.678-9") 
    @Column(unique = true)
    private String rut;
    
    @Schema(description = "Telefono del cliente", example = "912345678") 
    private int telefono;

    @Schema(description = "Correo electronico del cliente", example = "jperez@ejemplo.cl") 
    private String email;

    @Schema(description = "Fecha de registro en base de datos", example = "17/05/2024") 
    private LocalDate fecha_registro;

    @Schema(description = "Cantidad de ordenes (servicios) solicitados por el cliente", example = "3") 
    private int ordenes_totales;
    
    @Schema(description = "Lista de equipos del cliente. Se solicitan desde el microservicio 'equipos'.", accessMode = Schema.AccessMode.READ_ONLY) 
    @Transient
    private List<EquipoDTO> equipos;
}
