package com.pcer2.service_equipo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pcer2.service_equipo.model.TipoEquipo;
import com.pcer2.service_equipo.repository.TipoEquipoRepository;

@Service
public class TipoEquipoService {

    @Autowired
    private TipoEquipoRepository tipoEquipoRepository;

        public List<TipoEquipo> listarTiposEquipo() {
        return tipoEquipoRepository.findAll();
    }
    
    public Optional<TipoEquipo> buscarTipoEquipoPorId(Long id) {
        return tipoEquipoRepository.findById(id);
    }
    
    public Optional<TipoEquipo> buscarTipoEquipoPorNombre(String nombre) {
        return tipoEquipoRepository.findByNombre(nombre);
    }
    
    @Transactional
    public TipoEquipo guardarTipoEquipo(TipoEquipo tipoEquipo) {
        if (tipoEquipoRepository.existsByNombre(tipoEquipo.getNombre())) {
            throw new RuntimeException("Ya existe un tipo de equipo con el nombre: " + tipoEquipo.getNombre());
        }
        return tipoEquipoRepository.save(tipoEquipo);
    }
    
    @Transactional
    public void eliminarTipoEquipo(Long id) {
        if (!tipoEquipoRepository.existsById(id)) {
            throw new RuntimeException("Tipo de equipo no encontrado con ID: " + id);
        }
        tipoEquipoRepository.deleteById(id);
    }

    public boolean existePorNombre(String nombre) {
        return tipoEquipoRepository.existsByNombre(nombre);
    }    

}
