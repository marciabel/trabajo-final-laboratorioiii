package ar.utn.frbb.tup.persistence.implementation;

import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.persistence.CarreraDao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CarreraDaoImplementation implements CarreraDao {

    private static final Map<Integer, Carrera> repositorioCarreras = new HashMap<>();

    //private static final String FILE_PATH = "repositorioCarreras.json";

    @Override
    public Carrera crearCarrera(Carrera carrera) {

        repositorioCarreras.put(carrera.getIdCarrera(), carrera);
        //FileHandler.appendJson(convertObjectToJson(carrera),FILE_PATH);
        System.out.println(repositorioCarreras);

        return carrera;
    }

    @Override
    public Carrera getCarrera(Integer idCarrera) {
        if (!repositorioCarreras.containsKey(idCarrera)) {
            //Exception
        }
        return repositorioCarreras.get(idCarrera);
    }

    @Override
    public void deleteCarrera(Integer idCarrera) {
        repositorioCarreras.remove(idCarrera);
        System.out.println(repositorioCarreras);
    }

    @Override
    public Carrera updateCarrera(Integer idCarrera, Carrera carrera) {
        repositorioCarreras.put(idCarrera, carrera);
        return carrera;
    }


//    public static String convertObjectToJson(Object object) {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            // Convert the object to JSON string
//            return objectMapper.writeValueAsString(object);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return null; // Return null on failure (you can handle this differently as needed)
//    }



}
