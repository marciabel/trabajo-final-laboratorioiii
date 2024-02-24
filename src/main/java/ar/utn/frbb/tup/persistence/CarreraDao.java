package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.persistence.exception.CarreraAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;

public interface CarreraDao {
    Carrera createCarrera(Carrera carrera) throws CarreraAlreadyExistsException;

    Carrera getCarrera(Integer idCarrera) throws CarreraNotFoundException;

    void deleteCarrera(Integer idCarrera) throws CarreraNotFoundException;

    Carrera updateCarrera(Integer idCarrera, Carrera carrera) throws CarreraNotFoundException;

    Boolean existeCarrera(Integer idCarrera) throws CarreraNotFoundException;
}
