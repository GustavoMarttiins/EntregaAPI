package com.algaapi.service;

import com.algaapi.model.Cliente;
import com.algaapi.model.Entrega;
import com.algaapi.model.StatusEntrega;
import com.algaapi.repository.ClienteRepository;
import com.algaapi.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class EntregaService {

    private EntregaRepository entregaRepository;
    private CadastroClienteService cadastroClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega){
        Cliente cliente = cadastroClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
