package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.CarreraService;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;
import ar.utn.frbb.tup.dto.CarreraDTO;
import ar.utn.frbb.tup.model.Carrera;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class CarreraControllerTest {

    @InjectMocks
    CarreraController carreraController;

    @Mock
    CarreraService carreraService;

    MockMvc mockMvc;

    private CarreraDTO carreraDTO;
    private Carrera carrera;

    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(carreraController).build();
    }

    @Test
    public void testCrearCarrera() throws Exception {

        //Set up
        carreraDTO = new CarreraDTO();
        carreraDTO.setNombre("Ingeniería en Sistemas");
        carreraDTO.setCantidadCuatrimestres(12);

        carrera = new Carrera();
        carrera.setNombre(carreraDTO.getNombre());
        carrera.setCantidadCuatrimestres(carreraDTO.getCantidadCuatrimestres());

        when(carreraService.crearCarrera(any(CarreraDTO.class))).thenReturn(carrera);

        Carrera result = carreraController.crearCarrera(carreraDTO);

        assertNotNull(result);
        verify(carreraService).crearCarrera(any(CarreraDTO.class));
    }

    @Test
    public void testCrearCarreraBadRequest() throws Exception {

        //Set up
        carreraDTO = new CarreraDTO();
        carreraDTO.setNombre("Ingeniería en Sistemas");
        carreraDTO.setCantidadCuatrimestres(12);

        carrera = new Carrera();
        carrera.setNombre(carreraDTO.getNombre());
        carrera.setCantidadCuatrimestres(carreraDTO.getCantidadCuatrimestres());

        when(carreraService.crearCarrera(any(CarreraDTO.class))).thenThrow(new NombreInvalidoException("Nombre invalido"));
        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setIdCarrera(100);

        NombreInvalidoException exception = assertThrows(NombreInvalidoException.class, () -> {
            carreraController.crearCarrera(carreraDTO);
        });

        // Verificar que el servicio fue llamado con los datos proporcionados
        verify(carreraService).crearCarrera(any(CarreraDTO.class));

        // Verificar que el nombre del mensaje se manda correctamente
        assertEquals("Nombre invalido", exception.getMessage());
    }

    @Test
    void testModificarCarreraSuccess() throws Exception {
        // Datos de entrada
        Integer idCarrera = 1;
        Map<String, Object> campos = new HashMap<>();
        campos.put("nombre", "Ingeniería Informática Actualizada");
        Carrera carreraActualizada = new Carrera();
        carreraActualizada.setIdCarrera(idCarrera);
        carreraActualizada.setNombre("Ingeniería Informática Actualizada");

        // Configuración del mock para devolver la carrera modificada
        when(carreraService.modificarCarrera(eq(idCarrera), anyMap())).thenReturn(carreraActualizada);

        // Realizar una solicitud PATCH y verificar que se devuelve un estado OK con la carrera modificada
        mockMvc.perform(MockMvcRequestBuilders.patch("/carrera/{idCarrera}", idCarrera)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(campos))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testEliminarCarreraSuccess() throws Exception {
        // Datos de entrada
        Integer idCarrera = 1;
        String mensajeEsperado = "Carrera eliminada con exito.";

        // Configuración del mock para devolver un mensaje al eliminar la carrera
        when(carreraService.eliminarCarrera(idCarrera)).thenReturn(mensajeEsperado);

        // Realizar una solicitud DELETE y verificar que se devuelve un estado OK con el mensaje esperado
        mockMvc.perform(MockMvcRequestBuilders.delete("/carrera/{idCarrera}", idCarrera)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(mensajeEsperado))
                .andReturn();
    }
}
