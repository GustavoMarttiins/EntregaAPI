package com.algaapi.controller;

import com.algaapi.mapper.EntregaMapper;
import com.algaapi.request.EntregaRequest;
import com.algaapi.response.EntregaModel;
import com.algaapi.model.Entrega;
import com.algaapi.repository.EntregaRepository;
import com.algaapi.service.EntregaService;
import com.algaapi.service.FinalizacaoEntregaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entrega")
public class EntregaController {

    private EntregaService entregaService;
    private EntregaRepository entregaRepository;
    private EntregaMapper entregaMapper;
    private FinalizacaoEntregaService finalizacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaRequest entregaRequest){
        Entrega novaEntrega = entregaMapper.toEntity(entregaRequest);
        Entrega entregaSolicitada =  entregaService.solicitar(novaEntrega);
        return entregaMapper.toModel(entregaSolicitada);
    }

    @PutMapping("/{id}/finaliza")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long id){
        finalizacaoEntregaService.finalizar(id);

    }

    @GetMapping
    public List<EntregaModel> listar(){

        return entregaMapper.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long id){
        return entregaRepository.findById(id)
                .map(entrega -> ResponseEntity.ok(entregaMapper.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }
}
