package com.pcer2.service_estadisticas.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estadisticas")
public class Estadistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;

    private LocalDateTime fechaGeneracion;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String reporteJson;
    
}