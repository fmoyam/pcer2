package com.pcer2.service_software.model;

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
@Table(name = "licencias_software")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una licencia de software registrada en el sistema")
public class LicenciaSoftware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la licencia de software", example = "1")
    private Long id;

    @Schema(description = "Nombre del software", example = "Windows 11 Pro")
    private String nombre;

    @Schema(description = "Marca o proveedor del software", example = "Microsoft")
    private String marca;

    @Schema(description = "Versión del software", example = "11 Pro")
    private String version;

    @Column(unique = true)
    @Schema(description = "Serial único de la licencia", example = "WIN11-PRO-ABC123")
    private String serial;
}