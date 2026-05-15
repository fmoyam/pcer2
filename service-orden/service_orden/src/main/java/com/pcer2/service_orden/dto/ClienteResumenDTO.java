package com.pcer2.service_orden.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) 
public class ClienteResumenDTO {
    
    private String rut;
    private String nombre;
    private String apellido;

}
