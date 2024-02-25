package ar.utn.frbb.tup.persistence.implementation;

import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.persistence.CarreraDao;
import ar.utn.frbb.tup.persistence.exception.CarreraAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CarreraDaoImplementation implements CarreraDao {

    private static final Map<Integer, Carrera> repositorioCarreras = new HashMap<>();

    //private static final String FILE_PATH = "/src/main/java/ar.utn.frbb.tup/persistence/memory/repositorioCarreras.json";

    @Override
    public Carrera createCarrera(Carrera carrera) throws CarreraAlreadyExistsException {
        if (repositorioCarreras.containsKey(carrera.getIdCarrera())) {
            throw new CarreraAlreadyExistsException("El id ingresado ya pertenece a otra carrera");
        }

        repositorioCarreras.put(carrera.getIdCarrera(), carrera);
        System.out.println(repositorioCarreras);

        return carrera;
    }

    @Override
    public Carrera getCarrera(Integer idCarrera) throws CarreraNotFoundException {
        existeCarrera(idCarrera);
        return repositorioCarreras.get(idCarrera);
    }

    @Override
    public void deleteCarrera(Integer idCarrera) throws CarreraNotFoundException {
        existeCarrera(idCarrera);
        repositorioCarreras.remove(idCarrera);
        System.out.println(repositorioCarreras);
    }

    @Override
    public Carrera updateCarrera(Integer idCarrera, Carrera carrera) throws CarreraNotFoundException {
        existeCarrera(idCarrera);
        repositorioCarreras.put(idCarrera, carrera);
        return carrera;
    }

    public Boolean existeCarrera(Integer idCarrera) throws CarreraNotFoundException {
        if (!repositorioCarreras.containsKey(idCarrera)) {
            throw new CarreraNotFoundException("No se encontró la carrera con el id " + idCarrera);
        }
        return true;
    }


    public static String convertObjectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert the object to JSON string
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null; // Return null on failure (you can handle this differently as needed)
    }



}
