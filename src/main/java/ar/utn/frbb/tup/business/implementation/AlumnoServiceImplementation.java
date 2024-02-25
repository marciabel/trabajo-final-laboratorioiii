package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.*;
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
    MateriaService materiaService;

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
    public Alumno modificarAlumno(Integer idAlumno, Map<String, Object> atributos) throws AlumnoNoExisteException, ValorInvalidoException, CarreraNotFoundException {
        Alumno alumno = alumnoDao.getAlumnoById(idAlumno);

        for (Map.Entry<String, Object> atributo : atributos.entrySet()) {
            String nombreAtributo = atributo.getKey();
            Object valor = atributo.getValue();
            modificarAtributos(nombreAtributo, valor, alumno);
        }

        //alumnoDao.update(alumno);

        return alumno;
    }

    @Override
    public String eliminarAlumno(Integer idAlumno) throws AlumnoNoExisteException {
        return alumnoDao.deleteAlumno(idAlumno);
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
        }
        return null;
    }


    private void modificarAtributos(String nombreAtributo, Object value, Alumno alumno) throws ValorInvalidoException, CarreraNotFoundException {
        switch (nombreAtributo) {
            case "nombre" -> {
                if (value instanceof String) {
                    alumno.setNombre((String) value);
                }
            }
            case "apellido" -> {
                if (value instanceof String) {
                    alumno.setApellido((String) value);
                }
            }
            case "dni" -> {
                if (value instanceof Integer) {
                    validaciones.validarNumeroPositivo((Integer) value, "DNI");
                    alumno.setDni((Integer) value);
                }
            }
            case "carrera" -> {
                if (value instanceof Integer) {
                    alumno.setCarrera(carreraService.getCarreraById((Integer) value));
                }
            }
            case "asignaturas" -> {
                if (value instanceof List) {
                    agregarAsignaturas(alumno, (List<Integer>) value);
                }
            }
        }
    }

    private void agregarAsignaturas(Alumno alumno, List<Integer> idMaterias) throws ValorInvalidoException {
        for (Integer idMateria: idMaterias) {
            Asignatura asignatura = new Asignatura();
            asignatura.setMateria(materiaService.getMateriaById(idMateria));
            alumno.getAsignaturas().add(asignatura);
        }
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
