package com.pcer2.service_descuento.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pcer2.service_descuento.dto.DescuentoDto;
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

    public Descuento guardar(DescuentoDto dto){

        Descuento descuento = new Descuento();
        descuento.setCodigo(dto.getCodigo());
        descuento.setDescripcion(dto.getDescripcion());
        descuento.setPorcentajeDescuento(dto.getPorcentajeDescuento());
        descuento.setFechaInicio(dto.getFechaInicio());
        descuento.setFechaFin(dto.getFechaFin());

        descuento.setActivo(dto.getActivo());

        return descuentoRepository.save(descuento);
    }

    public void eliminar(Long id) {
        descuentoRepository.deleteById(id);
    }

    public boolean esValido(String codigo) {
    return descuentoRepository.findByCodigo(codigo)
        .map(descuento -> {
            LocalDate hoy = LocalDate.now();
            
            
            boolean estaActivo = Boolean.TRUE.equals(descuento.getActivo());
            boolean yaInicio = !hoy.isBefore(descuento.getFechaInicio());
            boolean noHaExpirado = descuento.getFechaFin() == null || !hoy.isAfter(descuento.getFechaFin());

            return estaActivo && yaInicio && noHaExpirado;
        })
        .orElse(false);
    }
    public Descuento obtenerPorCodigo(String codigo) {
        return descuentoRepository.findByCodigo(codigo).orElse(null);
    }
}
