package com.pcer2.service_software.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pcer2.service_software.dto.LicenciaSoftwareDTO;
import com.pcer2.service_software.model.LicenciaSoftware;
import com.pcer2.service_software.repository.LicenciaSoftwareRepository;

@Service
public class LicenciaSoftwareService {

    @Autowired
    private LicenciaSoftwareRepository licenciaSoftwareRepository;

    public LicenciaSoftware guardar(LicenciaSoftwareDTO licenciaSoftwareDTO) {

        LicenciaSoftware licenciaSoftware = new LicenciaSoftware();

        licenciaSoftware.setNombre(licenciaSoftwareDTO.getNombre());
        licenciaSoftware.setMarca(licenciaSoftwareDTO.getMarca());
        licenciaSoftware.setVersion(licenciaSoftwareDTO.getVersion());
        licenciaSoftware.setSerial(licenciaSoftwareDTO.getSerial());

        return licenciaSoftwareRepository.save(licenciaSoftware);
    }

    public List<LicenciaSoftware> listarTodos() {
        return licenciaSoftwareRepository.findAll();
    }

    public Optional<LicenciaSoftware> buscarPorId(Long id) {
        return licenciaSoftwareRepository.findById(id);
    }

    public LicenciaSoftware actualizarLicencia(Long id, LicenciaSoftwareDTO licenciaSoftwareDTO) {

        Optional<LicenciaSoftware> licenciaExistente = licenciaSoftwareRepository.findById(id);

        if (licenciaExistente.isPresent()) {

            LicenciaSoftware licenciaSoftware = licenciaExistente.get();

            licenciaSoftware.setNombre(licenciaSoftwareDTO.getNombre());
            licenciaSoftware.setMarca(licenciaSoftwareDTO.getMarca());
            licenciaSoftware.setVersion(licenciaSoftwareDTO.getVersion());
            licenciaSoftware.setSerial(licenciaSoftwareDTO.getSerial());

            return licenciaSoftwareRepository.save(licenciaSoftware);
        }

        return null;
    }

    public void eliminarLicencia(Long id) {
        licenciaSoftwareRepository.deleteById(id);
    }
}