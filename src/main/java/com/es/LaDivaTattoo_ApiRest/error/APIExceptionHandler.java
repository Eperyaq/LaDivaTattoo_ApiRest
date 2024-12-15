package com.es.LaDivaTattoo_ApiRest.error;

import com.es.LaDivaTattoo_ApiRest.error.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Controlador global para manejar excepciones en la API.
 * <p>
 * Esta clase captura excepciones específicas lanzadas por los controladores y devuelve una respuesta personalizada
 * con un mensaje de error adecuado y un código de estado HTTP correspondiente.
 * </p>
 */
@ControllerAdvice
public class APIExceptionHandler {

    /**
     * Maneja excepciones relacionadas con solicitudes incorrectas (código HTTP 400).
     * <p>
     * Se activará cuando se lance una {@link BadRequestException} o {@link IllegalArgumentException}.
     * </p>
     *
     * @param request la solicitud HTTP que causó la excepción.
     * @param e la excepción que fue lanzada.
     * @return un objeto {@link ErrorMessageForClient} con el mensaje de error y URI de la solicitud.
     */
    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageForClient BadRequestHandler(HttpServletRequest request, Exception e) {
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }

    /**
     * Maneja excepciones de no encontrado (código HTTP 404).
     * <p>
     * Se activa cuando se lanza una {@link NotFoundException}.
     * </p>
     *
     * @param request la solicitud HTTP que causó la excepción.
     * @param e la excepción que fue lanzada.
     * @return un objeto {@link ErrorMessageForClient} con el mensaje de error y URI de la solicitud.
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageForClient NotFoundHandler(HttpServletRequest request, Exception e) {
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }

    /**
     * Maneja excepciones de conflicto (código HTTP 409).
     * <p>
     * Se activa cuando se lanzan excepciones relacionadas con datos duplicados o violaciones de integridad.
     * Ejemplos: {@link DuplicateException}, {@link DataIntegrityViolationException}.
     * </p>
     *
     * @param request la solicitud HTTP que causó la excepción.
     * @param e la excepción que fue lanzada.
     * @return un objeto {@link ErrorMessageForClient} con el mensaje de error y URI de la solicitud.
     */
    @ExceptionHandler({DuplicateException.class, DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessageForClient handleDuplicate(HttpServletRequest request, Exception e) {
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }

    /**
     * Maneja excepciones de no autorizado (código HTTP 401).
     * <p>
     * Se activa cuando se lanzan excepciones relacionadas con la autenticación o autorización fallidas.
     * Ejemplos: {@link UnathorizedException}, {@link AuthenticationException}, {@link HttpClientErrorException.Forbidden}.
     * </p>
     *
     * @param request la solicitud HTTP que causó la excepción.
     * @param e la excepción que fue lanzada.
     * @return un objeto {@link ErrorMessageForClient} con el mensaje de error y URI de la solicitud.
     */
    @ExceptionHandler({UnathorizedException.class, AuthenticationException.class, HttpClientErrorException.Forbidden.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMessageForClient handleNotAuthorized(HttpServletRequest request, Exception e) {
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }

    /**
     * Maneja excepciones genéricas (código HTTP 500).
     * <p>
     * Se activa para cualquier excepción no específica o cuando ocurre un error interno inesperado.
     * Ejemplos: {@link GenericInternalException}, {@link Exception}.
     * </p>
     *
     * @param request la solicitud HTTP que causó la excepción.
     * @param e la excepción que fue lanzada.
     * @return un objeto {@link ErrorMessageForClient} con el mensaje de error y URI de la solicitud.
     */
    @ExceptionHandler({GenericInternalException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageForClient handleGeneric(HttpServletRequest request, Exception e) {
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }
}
