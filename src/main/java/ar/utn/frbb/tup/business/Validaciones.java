package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;

public interface Validaciones {
    String validarNombre(String nombre) throws NombreInvalidoException;

    Integer validarDepartamento(Integer departamento);

    Integer validarCantidadCuatrimestres(Integer cuatrimestres);

    Integer validarNumeroPositivo(Integer valor, String nombreAtributo) throws ValorInvalidoException;
}
