package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.business.exception.CantidadCuatrimestresInvalidException;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;
import ar.utn.frbb.tup.business.implementation.CarreraServiceImplementation;
import ar.utn.frbb.tup.dto.CarreraDTO;
import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.persistence.exception.CarreraAlreadyExistsException;
import ar.utn.frbb.tup.persistence.implementation.CarreraDaoImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class CarreraServiceTest {

    @InjectMocks
    CarreraServiceImplementation carreraService;

    @Mock
    CarreraDaoImplementation carreraDao;

    @BeforeEach
    void setUp() {


    }

    @Test
    void crearCarreraTest() throws CarreraAlreadyExistsException, NombreInvalidoException, CantidadCuatrimestresInvalidException, ValorInvalidoException {
        Carrera carreraExpected = new Carrera();
        carreraExpected.setIdCarrera(100);
        carreraExpected.setNombre("Tecnicatura Universitaria en Programacion");
        carreraExpected.setDepartamento(15);
        carreraExpected.setCantidadCuatrimestres(8);
        carreraExpected.setMaterias(new ArrayList<>());

        Mockito.when(carreraDao.createCarrera(any(Carrera.class))).thenReturn(carreraExpected);

        CarreraDTO carreraDTO = new CarreraDTO();
        carreraDTO.setIdCarrera(150);
        carreraDTO.setNombre("Tecnicatura Universitaria en Programacion");
        carreraDTO.setDepartamento(15);
        carreraDTO.setCantidadCuatrimestres(1);

        System.out.println("---------------------------------------------------------");
        System.out.println(carreraService.crearCarrera(carreraDTO));
        System.out.println("---------------------------------------------------------");


        Assertions.assertEquals(carreraExpected, carreraService.crearCarrera(carreraDTO));

        //Assertions.assertEquals("Tecnicatura Universitaria en Programacion", carreraService.crearCarrera(carreraDTO).getNombre());





    }

}
