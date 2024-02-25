package ar.utn.frbb.tup.persistence.implementation;

import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.persistence.AlumnoDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AlumnoDaoImplementation implements AlumnoDao {
    private static final Map<Integer, Alumno> repositorioAlumnos = new HashMap<>();
    @Override
    public Alumno createAlumno(Alumno a) {
        repositorioAlumnos.put(a.getIdAlumno(), a);
        System.out.println("Alumno guardado con exito");
        System.out.println(repositorioAlumnos);
        return a;
    }
}
