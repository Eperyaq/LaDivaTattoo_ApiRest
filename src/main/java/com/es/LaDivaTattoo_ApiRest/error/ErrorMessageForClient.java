package com.es.LaDivaTattoo_ApiRest.error;

/**
 * Clase para representar el mensaje de error que se enviará al cliente en caso de una excepción.
 * <p>
 * Esta clase contiene dos campos: el mensaje de error que describe la causa del problema y la URI
 * de la solicitud que causó el error. Estos detalles se incluyen en las respuestas de error
 * generadas por los controladores de excepciones en la API.
 * </p>
 */
public class ErrorMessageForClient {

    private String mensaje;  // El mensaje de error detallado.
    private String uri;      // La URI de la solicitud que causó el error.

    /**
     * Constructor que inicializa el mensaje de error y la URI.
     *
     * @param mensaje El mensaje de error que describe el problema.
     * @param uri La URI de la solicitud que causó el error.
     */
    public ErrorMessageForClient(String mensaje, String uri) {
        this.mensaje = mensaje;
        this.uri = uri;
    }

    /**
     * Obtiene el mensaje de error.
     *
     * @return El mensaje de error.
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Establece el mensaje de error.
     *
     * @param mensaje El mensaje de error que se quiere establecer.
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Obtiene la URI de la solicitud que causó el error.
     *
     * @return La URI de la solicitud.
     */
    public String getUri() {
        return uri;
    }

    /**
     * Establece la URI de la solicitud que causó el error.
     *
     * @param uri La URI que se quiere establecer.
     */
    public void setUri(String uri) {
        this.uri = uri;
    }
}
