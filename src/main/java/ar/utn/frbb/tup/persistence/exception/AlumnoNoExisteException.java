package ar.utn.frbb.tup.persistence.exception;

import lombok.Getter;

@Getter
public class AlumnoNoExisteException extends Exception {
    public AlumnoNoExisteException(String message) {
        super(message);
    }
}
