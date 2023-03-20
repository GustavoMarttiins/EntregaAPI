package com.algaapi.controller;

import com.algaapi.model.Cliente;
import com.algaapi.repository.ClienteRepository;
import com.algaapi.service.CadastroClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteRepository repository;
    private CadastroClienteService service;

    @GetMapping
    public List<Cliente> listar(){
        return repository.findAll();
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable("id") Long id){
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente cadastrar(@Valid @RequestBody Cliente cliente){

        return service.salvar(cliente);
    }

    @PutMapping("/alterando/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente){
        return repository.findById(id)
                .map(resp -> {resp.setName(cliente.getName());
                    resp.setEmail(cliente.getEmail());
                    resp.setPhone(cliente.getPhone());
                    Cliente update = service.salvar(resp);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> remove(@PathVariable("id") Long id){
        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }

            service.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }

}
