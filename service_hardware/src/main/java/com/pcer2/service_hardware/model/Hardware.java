package com.pcer2.service_hardware.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Hardware {

    @Schema(description = "ID único autoincremental.", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre de la pieza/hardware", example = "Adaptador PCI-X1 a NVME")
    private String nombre;

    @Schema(description = "Tipo de hardware", example = "Periferico")
    private String tipo; // ram, ssd, periferico, adaptador, etc.

    @Schema(description = "Fabricante de la pieza/hardware", example = "Ugreen")
    private String marca;

    @Schema(description = "Cantidad disponible en inventario", example = "66")
    private int cantidad;

    @Schema(description = "Descripcion y detalles adicionales del dispositivo", example = "RAM Compatible solo con AMD.")
    private String detalles; // info del producto

    @Schema(description = "Condicion del producto", example = "Usado, 98% de salud.")
    private String estado; // nuevo o usado

    @Schema(description = "Precio en pesos", example = "24990")
    private int precio;
}
