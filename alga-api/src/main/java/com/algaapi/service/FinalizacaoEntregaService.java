package com.algaapi.service;

import com.algaapi.exception.NegocioException;
import com.algaapi.model.Entrega;
import com.algaapi.model.StatusEntrega;
import com.algaapi.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private EntregaRepository entregaRepository;
    private BuscarEntregaService buscarEntregaService;

    @Transactional
    public void finalizar(Long id){
        Entrega entrega = buscarEntregaService.buscar(id);

        entrega.finalizar();
        entrega.setStatus(StatusEntrega.FINALIZADA);
        entregaRepository.save(entrega);
    }
}
