package ar.utn.frbb.tup.controller.handler;

import ar.utn.frbb.tup.business.exception.CantidadCuatrimestresInvalidException;
import ar.utn.frbb.tup.business.exception.DepartamentoInvalidoException;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;
import ar.utn.frbb.tup.persistence.exception.CarreraAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseCarreraExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler (value = {CantidadCuatrimestresInvalidException.class})
    protected ResponseEntity<Object> handleCantidadCuatrimestresInvalid(
            CantidadCuatrimestresInvalidException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler (value = {CarreraAlreadyExistsException.class})
    protected ResponseEntity<Object> handleCarreraAlreadyExists(
            CarreraAlreadyExistsException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler (value = {DepartamentoInvalidoException.class})
    protected ResponseEntity<Object> handleDepartamentoInvalido(
            DepartamentoInvalidoException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler (value = {NombreInvalidoException.class})
    protected ResponseEntity<Object> handleNombreInvalido(
            NombreInvalidoException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }


    @ExceptionHandler (value = {CarreraNotFoundException.class})
    protected ResponseEntity<Object> handleCarreraNotFound(
            CarreraNotFoundException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
               new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }
}
