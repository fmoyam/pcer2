package com.pcer2.service_equipo.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pcer2.service_equipo.model.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long>{

    List<Equipo> findByMarca(String marca);    

    Optional<Equipo> findByNumeroserie(String numeroserie);
    
    boolean existsByNumeroserie(String numeroserie);

    List<Equipo> findByClienteId(Long clienteId);    
    
}
