package com.pcer2.service_hardware.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pcer2.service_hardware.model.Hardware;
import com.pcer2.service_hardware.repository.HardwareRepository;

@Service
public class HardwareService {

    @Autowired
    private HardwareRepository hardwareRepository;

    public List<Hardware> listarTodos() {
        return hardwareRepository.findAll();
    }

    public Optional<Hardware> buscarPorId(Long id) {
        return hardwareRepository.findById(id);
    }

    public Hardware guardar(Hardware hardware) {
        return hardwareRepository.save(hardware);
    }

    public Hardware actualizar(Long id, Hardware hardwareActualizado) {

        return hardwareRepository.findById(id)
                .map(hardware -> {

                    hardware.setNombre(hardwareActualizado.getNombre());
                    hardware.setTipo(hardwareActualizado.getTipo());
                    hardware.setMarca(hardwareActualizado.getMarca());
                    hardware.setCantidad(hardwareActualizado.getCantidad());
                    hardware.setDetalles(hardwareActualizado.getDetalles());
                    hardware.setEstado(hardwareActualizado.getEstado());
                    hardware.setPrecio(hardwareActualizado.getPrecio());

                    return hardwareRepository.save(hardware);
                })
                .orElse(null);
    }

    public String eliminar(Long id) {
        Optional<Hardware> hardwareOpt = hardwareRepository.findById(id);
        
        if (hardwareOpt.isEmpty()) {
            return "Hardware no encontrado.";
        }
        
        Hardware hardware = hardwareOpt.get();
        
        if (hardware.getCantidad() > 1) {
            hardware.setCantidad(hardware.getCantidad() - 1);
            hardwareRepository.save(hardware);
            return "Cantidad reducida en 1. Nueva cantidad: " + hardware.getCantidad();
        }
        
        hardwareRepository.deleteById(id);
        return "Hardware eliminado.";
    }
}
