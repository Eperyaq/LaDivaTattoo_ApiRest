package com.es.LaDivaTattoo_ApiRest.error.exception;

/**
 * Excepción personalizada para manejar errores de solicitud incorrecta (HTTP 409).
 * <p>
 * Se lanza cuando una solicitud del cliente contiene datos duplicados.
 * </p>
 */
public class DuplicateException extends RuntimeException{
    private static final String DESCRIPCION = "Conflict (409)";

    public DuplicateException(String mensaje) {
        super(DESCRIPCION +". "+ mensaje);
    }
}

