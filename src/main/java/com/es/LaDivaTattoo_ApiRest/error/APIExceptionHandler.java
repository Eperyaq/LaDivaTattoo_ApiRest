package com.es.LaDivaTattoo_ApiRest.error;

import com.es.LaDivaTattoo_ApiRest.error.exception.BadRequestException;
import com.es.LaDivaTattoo_ApiRest.error.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class APIExceptionHandler {


    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageForClient BadRequestHandler(HttpServletRequest request, Exception e){

        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageForClient NotFoundHandler(HttpServletRequest request, Exception e){

        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());

    }


}
