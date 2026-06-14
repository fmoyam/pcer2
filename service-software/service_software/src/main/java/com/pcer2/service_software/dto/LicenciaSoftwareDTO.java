package com.pcer2.service_software.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenciaSoftwareDTO {

    private String nombre;
    private String marca;
    private String version;
    private String serial;
}