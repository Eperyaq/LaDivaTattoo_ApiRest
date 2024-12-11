package com.es.LaDivaTattoo_ApiRest.error.exception;

public class NotFoundException extends RuntimeException {

    private static final String DESCRIPCION = "Not found (404)";


    public NotFoundException(String mensaje){
        super(DESCRIPCION + ". " + mensaje);
    }

}
