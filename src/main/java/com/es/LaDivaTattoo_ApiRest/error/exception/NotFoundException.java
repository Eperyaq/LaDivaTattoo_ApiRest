package com.es.LaDivaTattoo_ApiRest.error.exception;
/**
 * Excepción personalizada para manejar errores de solicitud incorrecta (HTTP 404).
 * <p>
 * Se lanza cuando una solicitud del cliente realiza una solicitud y no se recibe nada de esta.
 * </p>
 */
public class NotFoundException extends RuntimeException {

    private static final String DESCRIPCION = "Not found (404)";


    public NotFoundException(String mensaje){
        super(DESCRIPCION + ". " + mensaje);
    }

}
