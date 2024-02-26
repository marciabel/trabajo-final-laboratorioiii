package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.model.Materia;
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

import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class MateriaControllerTest {

    @Mock
    private MateriaService materiaService;

    @InjectMocks
    private MateriaController materiaController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(materiaController)
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testModificarMateriaSuccess() throws Exception {
        Integer idMateria = 1;
        Map<String, Object> campos = Map.of("nombre", "Matematica");
        Materia materiaModificada = new Materia();
        materiaModificada.setNombre("Matematica");

        when(materiaService.modificarMateria(eq(idMateria), anyMap())).thenReturn(materiaModificada);

        mockMvc.perform(patch("/materia/{idMateria}", idMateria)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(campos)))
                .andExpect(status().isOk());
    }

    @Test
    void testEliminarMateriaSuccess() throws Exception {
        Integer idMateria = 1;
        String mensajeEsperado = "Materia eliminada con exito.";

        when(materiaService.eliminarMateria(idMateria)).thenReturn(mensajeEsperado);

        mockMvc.perform(delete("/materia/{idMateria}", idMateria))
                .andExpect(status().isOk())
                .andExpect(content().string(mensajeEsperado));
    }
}