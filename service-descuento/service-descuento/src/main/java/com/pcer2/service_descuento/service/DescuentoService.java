package com.pcer2.service_descuento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcer2.service_descuento.model.Descuento;
import com.pcer2.service_descuento.repository.DescuentoRepository;

@Service
public class DescuentoService {

    @Autowired
    private DescuentoRepository descuentoRepository;

    public List<Descuento> obtenerTodos() {
        return descuentoRepository.findAll();
    }

    public Descuento obtenerPorId(Long id) {
        return descuentoRepository.findById(id).orElse(null);
    }

    public Descuento guardar(Descuento descuento){
        return descuentoRepository.save(descuento);
    }

    public void eliminar(Long id) {
        descuentoRepository.deleteById(id);
    }

}
