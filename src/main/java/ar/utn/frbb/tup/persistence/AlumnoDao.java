package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNoExisteException;

import java.util.List;

public interface AlumnoDao {
    Alumno createAlumno(Alumno a) throws AlumnoAlreadyExistsException;

    Alumno getAlumnoById(Integer idAlumno) throws AlumnoNoExisteException;

    List<Asignatura> getAsignaturasByStudent(Integer idAlumno);

    String deleteAlumno(Integer idAlumno) throws AlumnoNoExisteException;
}
