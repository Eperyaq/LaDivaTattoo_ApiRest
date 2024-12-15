package com.es.LaDivaTattoo_ApiRest.error.exception;
/**
 * Excepción personalizada para manejar errores de solicitud incorrecta (HTTP 401).
 * <p>
 * Se lanza cuando el cliente no tiene los permisos necesarios para realizar una consulta.
 * </p>
 */
public class UnathorizedException extends RuntimeException{
    private static final String DESCRIPCION = "Not Authorized (401)";

    public UnathorizedException(String mensaje) {
        super(DESCRIPCION +". "+ mensaje);
    }
}
