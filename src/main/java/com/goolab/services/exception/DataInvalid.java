package com.goolab.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Entrada de dados inválido")
public class DataInvalid extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DataInvalid (String msg){
        super(msg);
    }

}
