package com.algaapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
           HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ExceptionCliente.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            campos.add(new ExceptionCliente.Campo(name, message));
        }

        ExceptionCliente exceptionCliente = new ExceptionCliente();
        exceptionCliente.setStatus(status.value());
        exceptionCliente.setDate(OffsetDateTime.now());
        exceptionCliente.setText("Um ou mais campos estão inválidos");
        exceptionCliente.setCampos(campos);
        return handleExceptionInternal(ex, exceptionCliente, headers, status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> entidadeNaoEncontrada(NegocioException exception, WebRequest request){
        HttpStatus status =  HttpStatus.NOT_FOUND;

        ExceptionCliente exceptionCliente = new ExceptionCliente();
        exceptionCliente.setStatus(status.value());
        exceptionCliente.setDate(OffsetDateTime.now());
        exceptionCliente.setText(exception.getMessage());


        return handleExceptionInternal(exception, exceptionCliente, new HttpHeaders(),status,request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException exception, WebRequest request){
        HttpStatus status =  HttpStatus.BAD_REQUEST;

        ExceptionCliente exceptionCliente = new ExceptionCliente();
        exceptionCliente.setStatus(status.value());
        exceptionCliente.setDate(OffsetDateTime.now());
        exceptionCliente.setText(exception.getMessage());


        return handleExceptionInternal(exception, exceptionCliente, new HttpHeaders(),status,request);
    }
}
