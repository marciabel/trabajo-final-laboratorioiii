package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.business.exception.CantidadCuatrimestresInvalidException;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;
import ar.utn.frbb.tup.business.implementation.CarreraServiceImplementation;
import ar.utn.frbb.tup.dto.CarreraDTO;
import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.CarreraAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import ar.utn.frbb.tup.persistence.implementation.CarreraDaoImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CarreraServiceTest {

    @InjectMocks
    CarreraServiceImplementation carreraService;

    @Mock
    CarreraDaoImplementation carreraDao;

    private CarreraDTO carreraDTO;
    private Carrera carrera;

    @BeforeEach
    void setUp() {
        // Inicializar los objetos de prueba
        carreraDTO = new CarreraDTO();
        carreraDTO.setIdCarrera(1);
        carreraDTO.setNombre("Ingeniería Informática");
        carreraDTO.setDepartamento(1);
        carreraDTO.setCantidadCuatrimestres(10);

        carrera = new Carrera();
        carrera.setIdCarrera(carreraDTO.getIdCarrera());
        carrera.setNombre(carreraDTO.getNombre());
        carrera.setDepartamento(carreraDTO.getDepartamento());
        carrera.setCantidadCuatrimestres(carreraDTO.getCantidadCuatrimestres());
        carrera.setMaterias(Collections.emptyList());

    }

    @Test
    void crearCarreraTest() throws CarreraAlreadyExistsException, NombreInvalidoException, CantidadCuatrimestresInvalidException, ValorInvalidoException {
        when(carreraDao.createCarrera(any(Carrera.class))).thenReturn(carrera);

        Carrera result = carreraService.crearCarrera(carreraDTO);

        assertNotNull(result);
        assertEquals(carreraDTO.getNombre(), result.getNombre());
        assertEquals(carreraDTO.getDepartamento(), result.getDepartamento());
        assertEquals(carreraDTO.getCantidadCuatrimestres(), result.getCantidadCuatrimestres());
        assertTrue(result.getMaterias().isEmpty());

        // Verifica que se llama al método createCarrera
        verify(carreraDao).createCarrera(any(Carrera.class));
    }

    @Test
    void testAgregarMateriaSuccess() {
        // Crear data
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(1);
        Materia materia = new Materia();
        materia.setMateriaId(1);

        // Simular que la materia se agregó correctamente
        doNothing().when(carreraDao).agregarMateriaACarrera(carrera, materia);

        // Test
        carreraService.agregarMateria(carrera, materia);

        // Verificar que el método agregarMateriaACarrera fue llamado con los argumentos correctos
        verify(carreraDao).agregarMateriaACarrera(carrera, materia);
    }

    @Test
    void testAgregarMateriaFailure() {
        // Crear una carrera y una materia para el test
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(1);
        Materia materia = new Materia();
        materia.setMateriaId(1);

        // Simular que ocurre un error al tratar de agregar la materia a la carrera
        doThrow(new RuntimeException("Carrera no encontrada")).when(carreraDao).agregarMateriaACarrera(carrera, materia);

        // Ejecutar el método a probar y esperar la excepción
        Exception exception = assertThrows(RuntimeException.class, () -> carreraService.agregarMateria(carrera, materia));

        // Verificar que la excepción tiene el mensaje correcto
        assertEquals("Carrera no encontrada", exception.getMessage());

        // Verificar que el método agregarMateriaACarrera fue llamado con los argumentos correctos
        verify(carreraDao).agregarMateriaACarrera(carrera, materia);
    }

    @Test
    void testEliminarCarreraSuccess() throws CarreraNotFoundException {
        Integer idCarrera = 1;
        String mensajeEsperado = "Carrera eliminada con éxito.";

        // Simular que la carrera se elimina correctamente y se retorna un mensaje
        when(carreraDao.deleteCarrera(idCarrera)).thenReturn(mensajeEsperado);

        // Ejecutar el método a probar
        String resultado = carreraService.eliminarCarrera(idCarrera);

        // Verificar el resultado
        assertEquals(mensajeEsperado, resultado);

        // Verificar que el método deleteCarrera fue llamado con el argumento correcto
        verify(carreraDao).deleteCarrera(idCarrera);
    }

    @Test
    void testEliminarCarreraFailure() throws CarreraNotFoundException {
        Integer idCarrera = 1;

        // Simular que la carrera no existe y se lanza una CarreraNotFoundException
        when(carreraDao.deleteCarrera(idCarrera)).thenThrow(new CarreraNotFoundException("Carrera no encontrada"));

        // Ejecutar el método a probar y esperar la excepción
        Exception exception = assertThrows(CarreraNotFoundException.class, () -> carreraService.eliminarCarrera(idCarrera));

        // Verificar que la excepción tiene el mensaje correcto
        assertEquals("Carrera no encontrada", exception.getMessage());
    }

    @Test
    void testGetCarreraByIdSuccess() throws CarreraNotFoundException {
        Integer idCarrera = 1;
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(idCarrera);
        carrera.setNombre("Ingeniería Informática");

        // Simular que se retorna la carrera cuando se busca por ID
        when(carreraDao.getCarrera(idCarrera)).thenReturn(carrera);

        // Ejecutar el método a probar
        Carrera resultado = carreraService.getCarreraById(idCarrera);

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(idCarrera, resultado.getIdCarrera());
        assertEquals("Ingeniería Informática", resultado.getNombre());

        // Verificar que el método getCarrera fue llamado con el argumento correcto
        verify(carreraDao).getCarrera(idCarrera);
    }

    @Test
    void testGetCarreraByIdFailure() throws CarreraNotFoundException {
        Integer idCarrera = 1;

        // Simular que no se encuentra la carrera y se lanza una CarreraNotFoundException
        when(carreraDao.getCarrera(idCarrera)).thenThrow(new CarreraNotFoundException("Carrera no encontrada"));

        // Ejecutar el método a probar y esperar la excepción
        Exception exception = assertThrows(CarreraNotFoundException.class, () -> carreraService.getCarreraById(idCarrera));

        // Verificar que la excepción tiene el mensaje correcto
        assertEquals("Carrera no encontrada", exception.getMessage());
    }


}
