package com.es.LaDivaTattoo_ApiRest.error.exception;

/**
 * Excepción personalizada para manejar errores de solicitud incorrecta (HTTP 400).
 * <p>
 * Se lanza cuando una solicitud del cliente contiene parámetros no válidos,
 * datos mal formateados o cualquier otro problema que impida procesar la solicitud.
 * </p>
 */
public class BadRequestException extends RuntimeException {

    /**
     * Descripción genérica de la excepción.
     */
    private static final String DESCRIPCION = "Bad request (400)";

    /**
     * Constructor para crear una instancia de BadRequestException con un mensaje detallado.
     *
     * @param mensaje descripción adicional del error ocurrido.
     */
    public BadRequestException(String mensaje) {
        super(DESCRIPCION + ". " + mensaje);
    }
}
