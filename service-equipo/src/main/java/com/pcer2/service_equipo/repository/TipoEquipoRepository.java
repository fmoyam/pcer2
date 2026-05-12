package com.pcer2.service_equipo.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcer2.service_equipo.model.TipoEquipo;

@Repository
public interface TipoEquipoRepository extends JpaRepository<TipoEquipo, Long>{
    
    Optional <TipoEquipo> findByNombre(String nombre);

    List<TipoEquipo> findAll();

    boolean existsByNombre(String nombre);

}
