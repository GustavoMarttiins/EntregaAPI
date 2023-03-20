package com.algaapi.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteIdRequest {

    @NotNull
    private Long id;
}
