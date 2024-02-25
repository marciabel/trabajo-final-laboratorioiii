package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.AlumnoService;
import ar.utn.frbb.tup.business.AsignaturaService;
import ar.utn.frbb.tup.business.CarreraService;
import ar.utn.frbb.tup.business.Validaciones;
import ar.utn.frbb.tup.business.exception.EstadoNoValidoException;
import ar.utn.frbb.tup.business.exception.NotaNoValidaException;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.dto.AlumnoDto;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.*;
import ar.utn.frbb.tup.persistence.AlumnoDao;
import ar.utn.frbb.tup.persistence.AsignaturaDao;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNoExisteException;
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
    AsignaturaDao asignaturaDao;

    @Autowired
    AsignaturaService asignaturaService;

    @Autowired
    CarreraService carreraService;

    @Autowired
    Validaciones validaciones;

    @Override
    public Alumno crearAlumno(AlumnoDto alumnoDTO) throws CarreraNotFoundException, ValorInvalidoException, AlumnoAlreadyExistsException {
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

    @Override
    public Alumno cambiarEstadoAsignatura(Integer idAlumno, String nombreAsignatura, AsignaturaDTO nuevoEstado) throws NotaNoValidaException, EstadoNoValidoException, AlumnoNoExisteException {
        Alumno alumno = alumnoDao.getAlumnoById(idAlumno);
        Asignatura asignatura = asignaturaService.getAsignaturaByName(alumnoDao.getAsignaturasByStudent(idAlumno), nombreAsignatura);

        if (alumno != null && asignatura != null) {
            validarNotaConEstado(nuevoEstado);
            asignatura.setEstado(nuevoEstado.getEstado());
            if (nuevoEstado.getEstado().equals(EstadoAsignatura.APROBADA)) {
                asignatura.setNota(nuevoEstado.getNota());
            }
            return alumno;
        } else {
            // Manejar el caso donde el alumno o la asignatura no se encuentran
            // Posiblemente lanzando una excepción personalizada
            System.out.println("Tirar excepcionnnioofjliasdf--------------------");
            System.out.println(idAlumno);
            System.out.println(nombreAsignatura);
            System.out.println(nuevoEstado);
            System.out.println("-----------------------");
            System.out.println(alumno);
            System.out.println(asignatura);
        }
        return null;
    }

    private void validarNotaConEstado(AsignaturaDTO nuevoEstado) throws NotaNoValidaException, EstadoNoValidoException {
        EstadoAsignatura estado = nuevoEstado.getEstado();
        Integer nota = nuevoEstado.getNota();

        if (estado.equals(EstadoAsignatura.APROBADA)) {
            if (nota < 5 || nota > 10) {
                throw new NotaNoValidaException("La nota ingresada no es valida para cambiar el estado de la asignatura a 'APROBADA'");
            }
        }
        else if (estado.equals(EstadoAsignatura.NO_CURSADA) || estado.equals(EstadoAsignatura.CURSADA)) {
            if (!(null == nota)) {
                throw new NotaNoValidaException("El estado de la materia ingresado no puede tener una nota asociada.");
            }
        }
        else {
            throw new EstadoNoValidoException("El estado ingresado no es valido. Estados validos: NO_CURSADA, CURSADA, APROBADA");
        }
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
