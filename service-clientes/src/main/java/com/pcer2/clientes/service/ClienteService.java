package com.pcer2.clientes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcer2.clientes.model.Cliente;
import com.pcer2.clientes.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id){
        return clienteRepository.findById(id);
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
