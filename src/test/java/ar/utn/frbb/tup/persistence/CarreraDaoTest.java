package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.persistence.exception.CarreraAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import ar.utn.frbb.tup.persistence.implementation.CarreraDaoImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarreraDaoTest {

    private CarreraDaoImplementation carreraDao;

    @BeforeEach
    void setUp() {
        carreraDao = new CarreraDaoImplementation();
    }

    @Test
    void testCreateCarreraSuccess() throws CarreraAlreadyExistsException {
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(1);

        Carrera result = carreraDao.createCarrera(carrera);

        assertNotNull(result);
        assertEquals(carrera.getIdCarrera(), result.getIdCarrera());
    }

    @Test
    void testCreateCarreraFailure() throws CarreraAlreadyExistsException {
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(2);
        // Set other properties of Carrera as needed
        carreraDao.createCarrera(carrera);

        CarreraAlreadyExistsException exception = assertThrows(
                CarreraAlreadyExistsException.class,
                () -> carreraDao.createCarrera(carrera)
        );

        assertEquals("El id ingresado ya pertenece a otra carrera", exception.getMessage());
    }

    @Test
    void testGetCarreraSuccess() throws CarreraNotFoundException, CarreraAlreadyExistsException {
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(3);

        carreraDao.createCarrera(carrera);

        Carrera result = carreraDao.getCarrera(3);

        assertNotNull(result);
        assertEquals(carrera.getIdCarrera(), result.getIdCarrera());

    }

    @Test
    void testGetCarreraFailure() {
        CarreraNotFoundException exception = assertThrows(
                CarreraNotFoundException.class,
                () -> carreraDao.getCarrera(4)
        );

        assertEquals("No se encontró la carrera con el id 4", exception.getMessage());
    }

    @Test
    void testDeleteCarreraSuccess() throws CarreraNotFoundException, CarreraAlreadyExistsException {
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(5);

        carreraDao.createCarrera(carrera);

        String result = carreraDao.deleteCarrera(5);

        assertEquals("Carrera 5 eliminada con exito", result);
    }

    @Test
    void testDeleteCarreraFailure() {
        CarreraNotFoundException exception = assertThrows(
                CarreraNotFoundException.class,
                () -> carreraDao.deleteCarrera(6)
        );

        assertEquals("No se encontró la carrera con el id 6", exception.getMessage());
    }

    @Test
    void testUpdateCarreraSuccess() throws CarreraNotFoundException, CarreraAlreadyExistsException {
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(7);

        carreraDao.createCarrera(carrera);

        Carrera nuevaCarrera = new Carrera();
        nuevaCarrera.setIdCarrera(7);
        nuevaCarrera.setNombre("Ingenieria en Sistemas");

        Carrera result = carreraDao.updateCarrera(7, nuevaCarrera);

        assertNotNull(result);
        assertEquals(nuevaCarrera.getNombre(), result.getNombre());
    }

    @Test
    void testUpdateCarreraFailure() {
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(1);

        CarreraNotFoundException exception = assertThrows(
                CarreraNotFoundException.class,
                () -> carreraDao.updateCarrera(8, carrera)
        );

        assertEquals("No se encontró la carrera con el id 8", exception.getMessage());
    }
}