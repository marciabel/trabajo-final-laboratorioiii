package ar.utn.frbb.tup.persistence.implementation;

import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.persistence.AlumnoDao;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNoExisteException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AlumnoDaoImplementation implements AlumnoDao {
    private static final Map<Integer, Alumno> repositorioAlumnos = new HashMap<>();
    @Override
    public Alumno createAlumno(Alumno a) throws AlumnoAlreadyExistsException {
        if (repositorioAlumnos.containsKey(a.getIdAlumno())) {
            throw new AlumnoAlreadyExistsException("El id ingresado ya existe en la base de datos");
        }
        repositorioAlumnos.put(a.getIdAlumno(), a);
        return a;
    }

    @Override
    public Alumno getAlumnoById(Integer idAlumno) throws AlumnoNoExisteException {
        if (!repositorioAlumnos.containsKey(idAlumno)) {
            throw new AlumnoNoExisteException("El id del alumno no existe");
        }
        return repositorioAlumnos.get(idAlumno);
    }

    @Override
    public Asignatura getAsignaturaByName(Integer idAlumno, String nombreAsignatura) {
        return null;
    }

    @Override
    public List<Asignatura> getAsignaturasByStudent(Integer idAlumno) {
        return repositorioAlumnos.get(idAlumno).getAsignaturas();
    }

    @Override
    public String deleteAlumno(Integer idAlumno) throws AlumnoNoExisteException {
        if (!repositorioAlumnos.containsKey(idAlumno)) {
            throw new AlumnoNoExisteException("El id ingresado no pertenece a ningun alumno");
        }
        repositorioAlumnos.remove(idAlumno);
        return ("Alumno eliminado con exito");
    }
}
