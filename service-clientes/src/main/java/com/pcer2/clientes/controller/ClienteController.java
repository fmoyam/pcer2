package com.pcer2.clientes.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pcer2.clientes.model.Cliente;
import com.pcer2.clientes.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Lista todos los clientes", description = "Obtiene todos los clientes presentes en BD 'pc_clientes'.")
    @GetMapping
    public List<Cliente> listar(){
        return clienteService.listarTodos();
    }

    @Operation(summary = "Busca un cliente mediante su RUT/RUN", description = "Filtra clientes mediante RUT/RUN en la BD 'pc_clientes'.")
    @GetMapping("/rut/{rut}")
    public ResponseEntity<Cliente> buscarPorRut(@PathVariable String rut) {
        Optional<Cliente> cliente = clienteService.buscarPorRut(rut);
        
        return cliente.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Busca un cliente mediante su ID", description = "Filtra clientes mediante ID en la BD 'pc_clientes'.")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        
        return cliente.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Crea un nuevo cliente", description = "Guarda los datos del nuevo cliente en BD 'pc_clientes'.")
    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.guardar(cliente));
    }

    @Operation(summary = "Actualiza un cliente mediante su ID", description = "Modifica los datos de un cliente existente en la BD 'pc_clientes'.")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(
            @PathVariable Long id,
            @RequestBody Cliente cliente) {

        if (clienteService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(id);

        return ResponseEntity.ok(clienteService.guardar(cliente));
    }    

    @Operation(summary = "Elimina un cliente mediante su ID", description = "Elimina los datos de un cliente en BD 'pc_clientes'.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
