package com.pcer2.service_equipo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="tipo_equipo")
@AllArgsConstructor
@NoArgsConstructor
public class TipoEquipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;  // pc (desktop), notebook, all in one, minipc, etc.

    @OneToMany(mappedBy = "tipoEquipo")
    @JsonIgnoreProperties("tipoEquipo")  // esto evita la recursión infinita
    private List<Equipo> equipos;   

}
