package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNoExisteException;
import ar.utn.frbb.tup.persistence.implementation.AlumnoDaoImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlumnoDaoTest {

    private AlumnoDaoImplementation alumnoDao;

    @BeforeEach
    void setUp() {
        alumnoDao = new AlumnoDaoImplementation();
    }

    @Test
    void testCreateAlumnoSuccess() throws AlumnoAlreadyExistsException {
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(1);

        Alumno result = alumnoDao.createAlumno(alumno);

        assertNotNull(result);
        assertEquals(alumno.getIdAlumno(), result.getIdAlumno());
    }

    @Test
    void testCreateAlumnoFailure() throws AlumnoAlreadyExistsException {
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(1);
        alumnoDao.createAlumno(alumno);

        AlumnoAlreadyExistsException exception = assertThrows(
                AlumnoAlreadyExistsException.class,
                () -> alumnoDao.createAlumno(alumno)
        );

        assertEquals("El id ingresado ya existe en la base de datos", exception.getMessage());
    }

    @Test
    void testGetAlumnoByIdSuccess() throws AlumnoNoExisteException, AlumnoAlreadyExistsException {
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(15);
        alumnoDao.createAlumno(alumno);

        Alumno result = alumnoDao.getAlumnoById(15);

        assertNotNull(result);
        assertEquals(alumno.getIdAlumno(), result.getIdAlumno());
    }

    @Test
    void testGetAlumnoByIdFailure() {
        AlumnoNoExisteException exception = assertThrows(
                AlumnoNoExisteException.class,
                () -> alumnoDao.getAlumnoById(3)
        );

        assertEquals("El id del alumno no existe", exception.getMessage());
    }

    @Test
    void testDeleteAlumnoSuccess() throws AlumnoNoExisteException, AlumnoAlreadyExistsException {
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(5);
        alumnoDao.createAlumno(alumno);

        String result = alumnoDao.deleteAlumno(1);

        assertEquals("Alumno eliminado con exito", result);
    }

    @Test
    void testDeleteAlumnoFailure() {
        AlumnoNoExisteException exception = assertThrows(
                AlumnoNoExisteException.class,
                () -> alumnoDao.deleteAlumno(8)
        );

        assertEquals("El id ingresado no pertenece a ningun alumno", exception.getMessage());
    }
}