package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.AlumnoService;
import ar.utn.frbb.tup.dto.AlumnoDto;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Asignatura;
import ar.utn.frbb.tup.model.Carrera;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AlumnoControllerTest {

    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(alumnoController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCrearAlumnoSuccess() throws Exception {
        // Crear y llenar el DTO de Alumno con datos de prueba
        AlumnoDto alumnoDTO = new AlumnoDto();
        alumnoDTO.setIdAlumno(1); // Asumiendo que este campo se auto-genera y sea necesario para la prueba
        alumnoDTO.setNombre("Juan");
        alumnoDTO.setApellido("Perez");
        alumnoDTO.setDni(12345678);
        alumnoDTO.setIdCarrera(100); // Asumiendo que es el ID de una carrera existente en tu sistema

        // Crear y llenar el Alumno con datos de prueba, incluyendo la carrera y asignaturas
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(1);
        alumno.setNombre("Juan");
        alumno.setApellido("Perez");
        alumno.setDni(12345678);

        // Inicializar y asignar una carrera al alumno (asumiendo que existe una clase Carrera)
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(100); // El mismo ID que en el DTO
        carrera.setNombre("Ingeniería en Sistemas"); // Ejemplo de nombre de carrera
        alumno.setCarrera(carrera);

        // Inicializar y asignar una lista de asignaturas al alumno (asumiendo que existe una clase Asignatura)
        Asignatura asignatura1 = new Asignatura();
        Asignatura asignatura2 = new Asignatura();

        List<Asignatura> asignaturas = Arrays.asList(asignatura1, asignatura2);
        alumno.setAsignaturas(asignaturas);

        // Configurar el mock del servicio para devolver el alumno cuando se llame a crearAlumno
        when(alumnoService.crearAlumno(any(AlumnoDto.class))).thenReturn(alumno);

        // Realizar la petición POST y verificar el resultado
        mockMvc.perform(post("/alumno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumnoDTO)))
                .andExpect(status().isOk());
    }
    @Test
    void testModificarAlumnoSuccess() throws Exception {
        Integer idAlumno = 1;
        Map<String, Object> campos = Map.of("nombre", "Nuevo Nombre");
        Alumno alumnoModificado = new Alumno(); // Inicializar con datos correspondientes

        when(alumnoService.modificarAlumno(eq(idAlumno), anyMap())).thenReturn(alumnoModificado);

        mockMvc.perform(patch("/alumno/{idAlumno}", idAlumno)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(campos)))
                .andExpect(status().isOk());
    }

    @Test
    void testEliminarAlumnoSuccess() throws Exception {
        Integer idAlumno = 1;
        String mensajeEsperado = "Alumno eliminado con éxito.";

        when(alumnoService.eliminarAlumno(idAlumno)).thenReturn(mensajeEsperado);

        mockMvc.perform(delete("/alumno/{idAlumno}", idAlumno))
                .andExpect(status().isOk())
                .andExpect(content().string(mensajeEsperado));
    }
}