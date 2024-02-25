package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.business.exception.EstadoNoValidoException;
import ar.utn.frbb.tup.business.exception.NotaNoValidaException;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.dto.AlumnoDto;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNoExisteException;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;

import java.util.Map;

public interface AlumnoService {
    Alumno crearAlumno(AlumnoDto alumnoDTO) throws CarreraNotFoundException, ValorInvalidoException, AlumnoAlreadyExistsException;

    Alumno modificarAlumno(Integer idAlumno, Map<String, Object> campos);

    void eliminarAlumno(Integer idAlumno);

    Alumno cambiarEstadoAsignatura(Integer idAlumno, String nombreAsignatura, AsignaturaDTO nuevoEstado) throws NotaNoValidaException, EstadoNoValidoException, AlumnoNoExisteException;
}
