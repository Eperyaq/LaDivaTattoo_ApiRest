package com.es.LaDivaTattoo_ApiRest.error.exception;

/**
 * Excepción personalizada para manejar errores de solicitud incorrecta (HTTP 500).
 * <p>
 * Se lanza cuando una solicitud del cliente es rechazada debido a un error en el servidor.
 * </p>
 */
public class GenericInternalException extends RuntimeException{

    private static final String DESCRIPCION = "Internal Server Error (500)";

    public GenericInternalException(String mensaje) {
        super(DESCRIPCION +". "+ mensaje);
    }
}
