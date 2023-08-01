package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.CarreraService;
import ar.utn.frbb.tup.dto.CarreraDTO;
import ar.utn.frbb.tup.model.Carrera;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class CarreraControllerTest {

    @InjectMocks
    CarreraController carreraController;

    @Mock
    CarreraService carreraService;

    MockMvc mockMvc;

    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(carreraController).build();
    }

    @Test
    public void crearCarreraTest() throws Exception {

//        Arrange
        Carrera carreraExpected = new Carrera();
        carreraExpected.setIdCarrera(100);
        carreraExpected.setNombre("Tecnicatura Universitaria en Programacion");
        carreraExpected.setDepartamento(15);
        carreraExpected.setCantidadCuatrimestres(8);
        carreraExpected.setMaterias(new ArrayList<>());

        Mockito.when(carreraService.crearCarrera(any(CarreraDTO.class))).thenReturn(carreraExpected);

        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setIdCarrera(100);
        carreraDTO.setNombre("Tecnicatura Universitaria en Programacion");
        carreraDTO.setDepartamento(15);
        carreraDTO.setCantidadCuatrimestres(8);

//        Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/carrera")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(carreraDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

//        Assert
        Assertions.assertEquals(carreraExpected, mapper.readValue(result.getResponse().getContentAsString(), Carrera.class));
    }

    @Test
    public void testCrearCarreraBadRequest() throws Exception {

        Mockito.when(carreraService.crearCarrera(any(CarreraDTO.class))).thenReturn(new Carrera());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/carrera")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "idCarrera": 36,
                                    "departamento": 45,
                                    "cantidadCuatrimestres": 8
                                }""")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andReturn();

    }

}
