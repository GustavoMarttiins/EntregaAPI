package com.algaapi.controller;

import com.algaapi.mapper.OcorrenciaMapper;
import com.algaapi.model.Entrega;
import com.algaapi.model.Ocorrencia;
import com.algaapi.request.OcorrenciaRequest;
import com.algaapi.response.OcorrenciaResponse;
import com.algaapi.service.BuscarEntregaService;
import com.algaapi.service.RegistroOcorrenciaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entrega/{id}/ocorrencias")
public class OcorrenciaController {

    private BuscarEntregaService buscarEntregaService;
    private RegistroOcorrenciaService ocorrenciaService;
    private OcorrenciaMapper ocorrenciaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaResponse registrar(@PathVariable Long id,
                                        @Valid @RequestBody OcorrenciaRequest ocorrenciaRequest){
        Ocorrencia ocorrenciaRegistrada = ocorrenciaService.registrar(id, ocorrenciaRequest.getDescricao());
        return ocorrenciaMapper.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaResponse> listar(@PathVariable Long id){
        Entrega entrega = buscarEntregaService.buscar(id);

        return ocorrenciaMapper.toCollection(entrega.getOcorrencias());
    }


}
