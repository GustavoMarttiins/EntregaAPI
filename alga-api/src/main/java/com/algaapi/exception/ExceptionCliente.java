package com.algaapi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ExceptionCliente {

    private Integer status;
    private OffsetDateTime date;
    private String text;
    private List<Campo> campos;

    @Getter
    @AllArgsConstructor
    public static class Campo{

        private String name;
        private String message;
    }
}
