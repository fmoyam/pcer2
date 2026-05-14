package com.pcer2.service_equipo.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pcer2.service_equipo.model.TipoAlmacen;
import com.pcer2.service_equipo.repository.TipoAlmacenRepository;
import jakarta.transaction.Transactional;

@Service
public class TipoAlmacenService {

    @Autowired
    private TipoAlmacenRepository tipoAlmacenRepository;

    public List<TipoAlmacen> listarTiposAlmacen() {
        return tipoAlmacenRepository.findAll();
    }
    
    public Optional<TipoAlmacen> buscarTipoAlmacenPorId(Long id) {
        return tipoAlmacenRepository.findById(id);
    }
    
    public Optional<TipoAlmacen> buscarTipoAlmacenPorNombre(String nombre) {
        return tipoAlmacenRepository.findByNombre(nombre);
    }
    
    @Transactional
    public TipoAlmacen guardarTipoAlmacen(TipoAlmacen tipoAlmacen) {
        if (tipoAlmacenRepository.existsByNombre(tipoAlmacen.getNombre())) {
            throw new RuntimeException("Ya existe un tipo de almacenamiento con el nombre: " + tipoAlmacen.getNombre());
        }
        return tipoAlmacenRepository.save(tipoAlmacen);
    }
    
    @Transactional
    public void eliminarTipoAlmacen(Long id) {
        if (!tipoAlmacenRepository.existsById(id)) {
            throw new RuntimeException("Tipo de almacenamiento no encontrado con ID: " + id);
        }
        tipoAlmacenRepository.deleteById(id);
    }

    public boolean existePorNombre(String nombre) {
        return tipoAlmacenRepository.existsByNombre(nombre);
    }    
        

}
