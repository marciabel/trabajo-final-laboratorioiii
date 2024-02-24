package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.AlumnoService;
import ar.utn.frbb.tup.dto.AlumnoDto;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.persistence.AlumnoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AlumnoServiceImplementation implements AlumnoService {

    @Autowired
    AlumnoDao alumnoDao;

    @Override
    public Alumno crearAlumno(AlumnoDto alumnoDTO) {
        return null;
    }

    @Override
    public Alumno modificarAlumno(Integer idAlumno, Map<String, Object> campos) {
        return null;
    }

    @Override
    public void eliminarAlumno(Integer idAlumno) {

    }
}
