package com.algaapi.service;

import com.algaapi.exception.NegocioException;
import com.algaapi.model.Cliente;
import com.algaapi.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CadastroClienteService {

    private ClienteRepository repository;

    public Cliente buscar(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new NegocioException("Cliente Não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente){
        boolean emailEmUso = repository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailEmUso){
            throw new NegocioException("Email já existente");
        }

        return repository.save(cliente);
    }

    @Transactional
    public void excluirCliente(Long id){
        repository.deleteById(id);
    }
}
