package com.pcer2.service_descuento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcer2.service_descuento.model.Descuento;

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long>{

    Optional<Descuento> findByCodigo(String codigo);
}
