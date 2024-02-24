package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.dto.AlumnoDto;
import ar.utn.frbb.tup.model.Alumno;

import java.util.Map;

public interface AlumnoService {
    Alumno crearAlumno(AlumnoDto alumnoDTO);

    Alumno modificarAlumno(Integer idAlumno, Map<String, Object> campos);

    void eliminarAlumno(Integer idAlumno);
}
