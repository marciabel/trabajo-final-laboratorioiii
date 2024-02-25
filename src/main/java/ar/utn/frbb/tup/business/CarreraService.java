package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;
import ar.utn.frbb.tup.dto.CarreraDTO;
import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.business.exception.CantidadCuatrimestresInvalidException;
import ar.utn.frbb.tup.persistence.exception.CarreraAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;

import java.util.List;
import java.util.Map;

public interface CarreraService {
    Carrera crearCarrera(CarreraDTO carreraDTO) throws CarreraAlreadyExistsException, CantidadCuatrimestresInvalidException, NombreInvalidoException, ValorInvalidoException;


    Carrera modificarCarrera(Integer idCarrera, Map<String, Object> atributos) throws CarreraNotFoundException, CantidadCuatrimestresInvalidException, NombreInvalidoException;

    void modificarAtributos(String nombreAtributo, Object value, Carrera carrera) throws CantidadCuatrimestresInvalidException, NombreInvalidoException;

    void agregarMateria(Carrera carrera, Materia materia);

    void eliminarCarrera(Integer idCarrera) throws CarreraNotFoundException;

    Carrera getCarreraById(Integer idCarrera) throws CarreraNotFoundException;


}
