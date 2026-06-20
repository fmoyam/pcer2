package com.pcer2.service_estadisticas.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estadisticas")
public class Estadistica {

    @Schema(description = "ID único autoincremental.", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Categoria del reporte estadistico", example = "CLIENTES")
    private String categoria;

    @Schema(description = "Fecha de generacion del reporte estadistico", example = "24/01/2025")
    private LocalDateTime fechaGeneracion;

    @Schema(description = "Resultado del reporte JSON")
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String reporteJson;
    
}