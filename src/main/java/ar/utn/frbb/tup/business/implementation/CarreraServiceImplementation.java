package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.CarreraService;
import ar.utn.frbb.tup.dto.CarreraDTO;
import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.CarreraDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class CarreraServiceImplementation implements CarreraService {

    @Autowired
    CarreraDao carreraDao;

    @Override
    public Carrera crearCarrera(CarreraDTO carreraDTO) {
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(carreraDTO.getIdCarrera());
        carrera.setDepartamento(carreraDTO.getDepartamento());
        carrera.setCantidadCuatrimestres(carreraDTO.getCantidadCuatrimestres());
        carrera.setMaterias(carreraDTO.getMaterias());

        carreraDao.crearCarrera(carrera);

        return carrera;
    }

    @Override
    public Carrera modificarCarrera(Integer idCarrera, Map<String, Object> atributos) {
        Carrera carrera = carreraDao.getCarrera(idCarrera);

        if (carrera != null) {
            for (Map.Entry<String, Object> atributo : atributos.entrySet()) {
                String attributeName = atributo.getKey();
                Object value = atributo.getValue();

                // Use getters and setters to handle the updates
                switch (attributeName) {
                    case "departamento" -> {
                        if (value instanceof Integer) {
                            carrera.setDepartamento((Integer) value);
                        }
                    }
                    case "cantidadCuatrimestres" -> {
                        if (value instanceof Integer) {
                            carrera.setCantidadCuatrimestres((Integer) value);
                        }
                    }
                    case "materias" -> {
                        if (value instanceof List) {
                            agregarMaterias(idCarrera, (List<Materia>) value);
                        }
                    }
                }
            }

            carreraDao.updateCarrera(idCarrera, carrera);
            System.out.println(carrera);
        }
        return carrera;
    }

    public void agregarMaterias(Integer idCarrera, List<Materia> materias) {
        Carrera carrera = carreraDao.getCarrera(idCarrera);
        for (Materia materia: materias) {
            if (materia instanceof Materia) {
                carrera.agregarMateria(materia);
            }
        }
    }

    @Override
    public void eliminarCarrera(Integer idCarrera) {
        carreraDao.deleteCarrera(idCarrera);
    }

    @Override
    public Carrera getCarreraById(Integer idCarrera) {
        return null;
    }


}
