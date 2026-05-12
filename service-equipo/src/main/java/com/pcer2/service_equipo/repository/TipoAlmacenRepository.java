package com.pcer2.service_equipo.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcer2.service_equipo.model.TipoAlmacen;

@Repository
public interface TipoAlmacenRepository extends JpaRepository<TipoAlmacen, Long>{

    Optional<TipoAlmacen> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
