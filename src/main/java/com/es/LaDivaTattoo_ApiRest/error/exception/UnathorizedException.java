package com.es.LaDivaTattoo_ApiRest.error.exception;

public class UnathorizedException extends RuntimeException{
    private static final String DESCRIPCION = "Not Authorized (401)";

    public UnathorizedException(String mensaje) {
        super(DESCRIPCION +". "+ mensaje);
    }
}
