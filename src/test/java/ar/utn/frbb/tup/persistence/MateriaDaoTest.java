package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.business.exception.MateriaNoExisteException;
import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;
import ar.utn.frbb.tup.persistence.implementation.MateriaDaoImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MateriaDaoTest {

    private MateriaDaoImplementation materiaDao;

    @BeforeEach
    void setUp() {
        materiaDao = new MateriaDaoImplementation();
    }

    @Test
    void testUpdateMateria() {
        Materia materia = new Materia();
        materia.setMateriaId(1);
        materia.setNombre("Programación");

        Materia updatedMateria = new Materia();
        updatedMateria.setMateriaId(1);
        updatedMateria.setNombre("Programación Avanzada");

        Materia result = materiaDao.updateMateria(1, updatedMateria);

        assertEquals(updatedMateria, result);
    }

    @Test
    void testDeleteMateriaFailure() {
        MateriaNoExisteException exception = assertThrows(
                MateriaNoExisteException.class,
                () -> materiaDao.deleteMateria(1)
        );

        assertEquals("La materia que esta intentando eliminar no existe", exception.getMessage());
    }

}