package com.algaapi.service;

import com.algaapi.exception.NegocioException;
import com.algaapi.model.Entrega;
import com.algaapi.model.Ocorrencia;
import com.algaapi.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private BuscarEntregaService buscarEntregaService;

    @Transactional
    public Ocorrencia registrar(Long id, String descricao) {
        Entrega entraga = buscarEntregaService.buscar(id);

        return entraga.adicionarOcorrencia(descricao);

    }
}
