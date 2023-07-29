package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.dto.CarreraDTO;
import ar.utn.frbb.tup.model.Carrera;

import java.util.Map;

public interface CarreraService {
    Carrera getCarreraById(Integer idCarrera);

    Carrera crearCarrera(CarreraDTO carreraDTO);

    Carrera modificarCarrera(Integer idCarrera, Map<String, Object> atributos);

    void eliminarCarrera(Integer idCarrera);
}
