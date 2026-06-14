package com.pcer2.service_mantenimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pcer2.service_mantenimiento.model.ProductoMantenimiento;

@Repository
public interface ProductoMantenimientoRepository extends JpaRepository<ProductoMantenimiento, Long> {

}
