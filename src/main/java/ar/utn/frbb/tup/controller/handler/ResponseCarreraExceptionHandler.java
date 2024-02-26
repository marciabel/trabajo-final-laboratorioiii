package ar.utn.frbb.tup.controller.handler;

import ar.utn.frbb.tup.business.exception.*;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
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

    @ExceptionHandler (value = {MateriaAlreadyExistsException.class})
    protected ResponseEntity<Object> handleMateriaAlreadyExists(
            MateriaAlreadyExistsException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler (value = {MateriaNoExisteException.class})
    protected ResponseEntity<Object> handleMateriaDoesntExists(
            MateriaNoExisteException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler (value = {ValorInvalidoException.class})
    protected ResponseEntity<Object> handleValorInvalido(
            ValorInvalidoException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler (value = {NotaNoValidaException.class})
    protected ResponseEntity<Object> handleValorInvalido(
            NotaNoValidaException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler (value = {EstadoNoValidoException.class})
    protected ResponseEntity<Object> handleValorInvalido(
            EstadoNoValidoException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler (value = {NombreInvalidoException.class})
    public ResponseEntity<Object> handleNombreInvalido(
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

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            CustomApiError error = new CustomApiError();
            error.setErrorMessage(ex.getMessage());
            body = error;
        }

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler (value = {AlumnoAlreadyExistsException.class})
    protected ResponseEntity<Object> handleMateriaAlreadyExists(
            AlumnoAlreadyExistsException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler (value = {AlumnoNoExisteException.class})
    protected ResponseEntity<Object> handleMateriaAlreadyExists(
            AlumnoNoExisteException ex, WebRequest request) {
        String exceptionMessage = ex.getMessage();
        CustomApiError error = new CustomApiError();
        error.setErrorMessage(exceptionMessage);
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }
}
