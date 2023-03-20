package com.algaapi.mapper;

import com.algaapi.model.Ocorrencia;
import com.algaapi.request.OcorrenciaRequest;
import com.algaapi.response.OcorrenciaResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {

    private ModelMapper modelMapper;

    public OcorrenciaResponse toModel(Ocorrencia ocorrencia){
        return modelMapper.map(ocorrencia, OcorrenciaResponse.class);
    }

    public List<OcorrenciaResponse> toCollection(List<Ocorrencia> ocorrencias){
        return ocorrencias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}
