package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.AlumnoService;
import ar.utn.frbb.tup.business.CarreraService;
import ar.utn.frbb.tup.business.Validaciones;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.dto.AlumnoDto;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.AlumnoDao;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AlumnoServiceImplementation implements AlumnoService {

    @Autowired
    AlumnoDao alumnoDao;

    @Autowired
    CarreraService carreraService;

    @Autowired
    Validaciones validaciones;

    @Override
    public Alumno crearAlumno(AlumnoDto alumnoDTO) throws CarreraNotFoundException, ValorInvalidoException {
        Alumno a = new Alumno();

        a.setIdAlumno(validaciones.validarNumeroPositivo(alumnoDTO.getIdAlumno(), "Id Alumno"));
        a.setNombre(alumnoDTO.getNombre());
        a.setApellido(alumnoDTO.getApellido());
        a.setDni(validaciones.validarNumeroPositivo(alumnoDTO.getDni(), "DNI"));
        Carrera carrera = carreraService.getCarreraById(alumnoDTO.getIdCarrera());
        a.setCarrera(carrera);

        a.setAsignaturas(cargarAsignaturas(carrera));

        return alumnoDao.createAlumno(a);
    }

    @Override
    public Alumno modificarAlumno(Integer idAlumno, Map<String, Object> campos) {
        return null;
    }

    @Override
    public void eliminarAlumno(Integer idAlumno) {

    }

    private List<Asignatura> cargarAsignaturas(Carrera carreraAlumno) {
        List<Materia> materiasAlumno = carreraAlumno.getMaterias();
        List<Asignatura> asignaturasAlumno = new ArrayList<>();

        for (Materia m: materiasAlumno) {
            Asignatura a = new Asignatura();
            a.setMateria(m);
            asignaturasAlumno.add(a);
        }

        return asignaturasAlumno;
    }
}
