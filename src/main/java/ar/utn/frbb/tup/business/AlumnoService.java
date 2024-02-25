package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.dto.AlumnoDto;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;

import java.util.Map;

public interface AlumnoService {
    Alumno crearAlumno(AlumnoDto alumnoDTO) throws CarreraNotFoundException, ValorInvalidoException;

    Alumno modificarAlumno(Integer idAlumno, Map<String, Object> campos);

    void eliminarAlumno(Integer idAlumno);
}
