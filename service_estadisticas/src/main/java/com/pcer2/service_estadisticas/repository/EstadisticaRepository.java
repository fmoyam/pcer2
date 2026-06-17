package com.pcer2.service_estadisticas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pcer2.service_estadisticas.model.Estadistica;

public interface EstadisticaRepository
        extends JpaRepository<Estadistica, Long> {
}
