package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.business.exception.MateriaNoExisteException;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.business.implementation.MateriaServiceImplementation;
import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.MateriaDao;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MateriaServiceTest {

    @Mock
    private MateriaDao materiaDao;

    @Mock
    private ProfesorService profesorService;

    @Mock
    private CarreraService carreraService;

    @Mock
    private Validaciones validaciones;

    @InjectMocks
    private MateriaServiceImplementation materiaService;

    private MateriaDTO materiaDTO;
    private Materia materia;
    @Test
    void testBuscarPorNombreSuccess() throws MateriaNoExisteException {
        String nombreBuscado = "Programacion";
        Materia m1 = new Materia();
        m1.setNombre("Programacion");
        Materia m2 = new Materia();
        m2.setNombre("Programacion");
        List<Materia> materiasEsperadas = Arrays.asList(m1, m2); // Suponemos que hay al menos dos materias con ese nombre

        // Configurar el mock para devolver una lista de materias cuando se busque por el nombre
        when(materiaDao.getMateriaByName(nombreBuscado)).thenReturn(materiasEsperadas);

        // Ejecutar el método a probar
        List<Materia> resultado = materiaService.buscarPorNombre(nombreBuscado);

        // Verificar los resultados
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(materiasEsperadas.size(), resultado.size());
    }

    @Test
    void testBuscarPorNombreFailure() {
        String nombreBuscado = "Programacion";

        // Devolver una lista vacía cuando no se encuentren materias con ese nombre
        when(materiaDao.getMateriaByName(nombreBuscado)).thenReturn(new ArrayList<>());

        // Ejecutar el método a probar y esperar la excepción
        Exception exception = assertThrows(MateriaNoExisteException.class, () -> materiaService.buscarPorNombre(nombreBuscado));

        // Verificar que la excepción tiene el mensaje correcto
        assertEquals("No existen materias para el filtro ingresado", exception.getMessage());

        // Verificar que el método getMateriaByName fue llamado con el argumento correcto
        verify(materiaDao).getMateriaByName(nombreBuscado);
    }

    @Test
    void testListarTodasLasMaterias() {
        List<Materia> materiasEsperadas = Arrays.asList(new Materia(), new Materia());
        // Suponemos que hay materias en la base de datos

        // Devolver una lista de materias cuando se soliciten todas las materias
        when(materiaDao.getAllMaterias()).thenReturn(materiasEsperadas);

        // Ejecutar el método a probar
        List<Materia> resultado = materiaService.listarTodasLasMaterias();

        // Verificar los resultados
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(materiasEsperadas.size(), resultado.size());

        // Verificar que el método getAllMaterias fue llamado
        verify(materiaDao).getAllMaterias();
    }

    @Test
    void testGetMateriaByIdSuccess() throws ValorInvalidoException {
        Integer idMateria = 1;
        Materia materiaEsperada = new Materia();
        materiaEsperada.setMateriaId(idMateria);
        materiaEsperada.setNombre("Programación");

        // Configurar el mock para validar el número positivo y devolver una materia cuando se busque por ID
        when(validaciones.validarNumeroPositivo(idMateria, "idMateria")).thenReturn(idMateria);
        when(materiaDao.getMateriaById(idMateria)).thenReturn(materiaEsperada);

        // Test
        Materia resultado = materiaService.getMateriaById(idMateria);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(idMateria, resultado.getMateriaId());
        assertEquals("Programación", resultado.getNombre());

        verify(validaciones).validarNumeroPositivo(idMateria, "idMateria");
        verify(materiaDao).getMateriaById(idMateria);
    }

    @Test
    void testEliminarMateriaSuccess() throws MateriaNoExisteException {
        Integer idMateria = 1;
        String mensajeEsperado = "Materia eliminada con éxito.";

        // Configurar el mock para devolver un mensaje cuando se elimine la materia
        when(materiaDao.deleteMateria(idMateria)).thenReturn(mensajeEsperado);

        // Test
        String resultado = materiaService.eliminarMateria(idMateria);

        // Verificar los resultados
        assertEquals(mensajeEsperado, resultado);

        verify(materiaDao).deleteMateria(idMateria);
    }

    @Test
    void testModificarMateriaSuccess() throws CarreraNotFoundException {
        Integer idMateria = 1;
        String nuevoNombre = "Programacion I";
        Integer nuevoAnio = 2;
        Map<String, Object> atributos = new HashMap<>();
        atributos.put("nombre", nuevoNombre);
        atributos.put("anio", nuevoAnio);

        Materia materia = new Materia();
        materia.setMateriaId(idMateria);
        materia.setNombre("Programación");
        materia.setAnio(1);

        // Devolver la materia existente cuando se busque por ID
        when(materiaDao.getMateriaById(idMateria)).thenReturn(materia);

        // Ejecutar el método a probar
        Materia resultado = materiaService.modificarMateria(idMateria, atributos);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(nuevoNombre, resultado.getNombre());
        assertEquals(nuevoAnio, resultado.getAnio());

        // Verificar que el método getMateriaById fue llamado con el argumento correcto
        verify(materiaDao).getMateriaById(idMateria);}


}
