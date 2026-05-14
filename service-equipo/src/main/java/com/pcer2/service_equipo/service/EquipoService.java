package com.pcer2.service_equipo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pcer2.service_equipo.model.Equipo;
import com.pcer2.service_equipo.repository.EquipoRepository;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> listarTodos() {
        return equipoRepository.findAll();
    }
    
    public Optional<Equipo> findById(Long id) {
        return equipoRepository.findById(id);
    }
    
    public Optional<Equipo> buscarPorNumeroSerie(String numeroserie) {
        return equipoRepository.findByNumeroserie(numeroserie);
    }
    
    public List<Equipo> buscarPorMarca(String marca) {
        return equipoRepository.findByMarca(marca);
    }
    
    @Transactional
    public Equipo guardar(Equipo equipo) {
        return equipoRepository.save(equipo);
    }
    
    @Transactional
    public void eliminar(Long id) {
        if (!equipoRepository.existsById(id)) {
            throw new RuntimeException("Equipo no encontrado con ID: " + id);
        }
        equipoRepository.deleteById(id);
    }

    public boolean existePorNumeroserie(String numeroserie) {  
        return equipoRepository.existsByNumeroserie(numeroserie);
    }

    public List<Equipo> findByClienteId(Long clienteId) {
        return equipoRepository.findByClienteId(clienteId);
    }    
    
}
