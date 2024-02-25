package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.Validaciones;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class ValidacionesImplementation implements Validaciones {

    @Override
    public String validarNombre(String nombre) throws NombreInvalidoException {
        if (nombre == null) {
            throw new NombreInvalidoException("El nombre no puede ser un valor nulo");
        }
        else if (nombre.length() < 5 || nombre.length() >=50) {
            throw new NombreInvalidoException("El nombre ingresado no es válido. Deberia tener entre 5 y 50 caracteres, y tiene: " + nombre.length() + " caracteres");
        }

        return nombre;
    }

    @Override
    public Integer validarDepartamento(Integer departamento) {
        return null;
    }

    @Override
    public Integer validarCantidadCuatrimestres(Integer cuatrimestres) {
        return null;
    }

    @Override
    public Integer validarNumeroPositivo(Integer valor, String nombreAtributo) throws ValorInvalidoException {
        if (valor == null) {
            throw new ValorInvalidoException("El valor ingresado para " + nombreAtributo + "no puede ser nulo.");
        }
        else if (valor < 0) {
            throw new ValorInvalidoException("El valor ingresado para " + nombreAtributo + " no puede ser negativo");
        }
        return valor;
    }

}
