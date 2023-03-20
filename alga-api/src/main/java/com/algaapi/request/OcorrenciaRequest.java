package com.algaapi.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OcorrenciaRequest {

    @NotBlank
    private String descricao;
}
