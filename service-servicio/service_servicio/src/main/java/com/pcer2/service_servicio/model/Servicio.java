package com.pcer2.service_servicio.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "servicios")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un servicio técnico disponible en el sistema")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del servicio técnico", example = "1")
    private Long id;

    @Schema(description = "Nombre del servicio técnico", example = "Mantención completa de equipo")
    private String nombre;

    @Schema(description = "Descripción detallada del servicio técnico", example = "Limpieza completa del hardware del equipo. Incluye cambio de pasta térmica a CPU y GPU.")
    private String descripcion;

    @Column(name = "precio_base")
    @Schema(description = "Precio base del servicio técnico", example = "35000")
    private Double precioBase;

    @Schema(description = "Estado del servicio técnico", example = "true")
    private Boolean activo = true;
}
