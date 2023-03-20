package com.algaapi.service;

import com.algaapi.exception.EntidadeNaoEncontradaException;
import com.algaapi.exception.NegocioException;
import com.algaapi.model.Entrega;
import com.algaapi.model.Ocorrencia;
import com.algaapi.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscarEntregaService {

    private EntregaRepository entregaRepository;

    public Entrega buscar(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }
}
