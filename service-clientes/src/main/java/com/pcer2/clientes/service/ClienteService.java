package com.pcer2.clientes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcer2.clientes.dto.EquipoDTO;
import com.pcer2.clientes.model.Cliente;
import com.pcer2.clientes.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
        
    @Autowired
    private EquipoClienteService equipoClientService;

    public List<Cliente> listarTodos(){
        List<Cliente> clientes = clienteRepository.findAll();
        // Cargar equipos para cada cliente
        clientes.forEach(cliente -> {
            List<EquipoDTO> equipos = equipoClientService.getEquiposByClienteId(cliente.getId());
            cliente.setEquipos(equipos);
        });
        return clientes;
    }

    public Optional<Cliente> buscarPorId(Long id){
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        clienteOpt.ifPresent(cliente -> {
            List<EquipoDTO> equipos = equipoClientService.getEquiposByClienteId(cliente.getId());
            cliente.setEquipos(equipos);
        });
        return clienteOpt;
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarPorRut(String rut) {
        return clienteRepository.findByRut(rut);
    }
    
    public Cliente guardar(Cliente cliente){
        return clienteRepository.save(cliente);
    }
    
    public void eliminarCliente(Long id){
        clienteRepository.deleteById(id);
    }

}
