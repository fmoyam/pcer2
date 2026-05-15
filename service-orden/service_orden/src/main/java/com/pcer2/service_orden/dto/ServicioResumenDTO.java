package com.pcer2.service_orden.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) //Si vienen campos extra se Ignoran
public class ServicioResumenDTO {

    private Long id;
    private String nombre;
}